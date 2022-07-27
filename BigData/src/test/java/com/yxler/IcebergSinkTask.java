package com.yxler;

import com.google.common.collect.Maps;
import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.*;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.data.GenericAppenderFactory;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.io.BaseTaskWriter;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class IcebergSinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(IcebergSinkTask.class);

    private Map<String, String> properties;
    private final Map<TopicPartition, BaseTaskWriter> writers = Maps.newHashMap();
    private FileFormat fileFormat;
    private GenericAppenderFactory appenderFactory;
    private Table table;
    private Catalog catalog;
    private int sinkTaskId;
    private long targetFileSize = 50L * 1024 * 1024;


    public IcebergSinkTask() {
    }

    @Override
    public void start(Map<String, String> props) {
        this.properties = props;
        String catalogName = properties.get("kafka.iceberg.catalog.name");
        String catalogImpl = properties.get("kafka.iceberg.catalog.impl");
        String tableIdentify = properties.get("kafka.iceberg.sink.table");
        Configuration hadoopConfiguration = new Configuration();
        this.catalog = CatalogUtil.loadCatalog(catalogImpl, catalogName, props, hadoopConfiguration);
        TableIdentifier tableIdentifier = TableIdentifier.of(tableIdentify);
        if (catalog.tableExists(tableIdentifier)) {
            throw new IllegalArgumentException(String.format("iceberg table {} not exist", tableIdentify));
        }

        this.table = catalog.loadTable(tableIdentifier);
        fileFormat = getFileFormat(table.properties(), props);
        this.appenderFactory = new GenericAppenderFactory(table.schema());
        this.sinkTaskId = Integer.parseInt(props.get("kafka.iceberg.sink.task.id"));
        if (props.containsKey("kafka.iceberg.sink.task.targetFileSize")) {
            this.targetFileSize = Long.parseLong(props.get("kafka.iceberg.sink.task.targetFileSize"));
        }

        Set<TopicPartition> assignedPartition = this.context.assignment();
        if (table.spec().isUnpartitioned()) {
            for (TopicPartition topicPartition : assignedPartition) {
                OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, topicPartition.partition(), sinkTaskId).format(fileFormat).build();
                KafkaUnpartitionedWriter<Record> unpartitionedWriter = new KafkaUnpartitionedWriter<Record>(table.spec(), fileFormat, appenderFactory, outputFileFactory, table.io(), targetFileSize);
                writers.put(topicPartition, unpartitionedWriter);
            }
        } else {
            for (TopicPartition topicPartition : assignedPartition) {
                OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, topicPartition.partition(), sinkTaskId).format(fileFormat).build();
                KafkaPartitionedWriter<Record> kafkaPartitionedWriter = new KafkaPartitionedWriter<Record>(table.spec(), fileFormat, appenderFactory, outputFileFactory, table.io(), targetFileSize, table.schema());
                writers.put(topicPartition, kafkaPartitionedWriter);
            }
        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for (SinkRecord record : records) {
            TopicPartition topicPartition = new TopicPartition(record.topic(), record.kafkaPartition());
            try {
                writers.get(topicPartition).write(record);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void stop() {
        for (BaseTaskWriter taskWriter : writers.values()) {
            try {
                taskWriter.close();
            } catch (IOException e) {
                log.error("task writer close error", e);
            }
        }
    }


    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        AppendFiles appendFiles = table.newAppend();
        boolean fail = false;
        try {
            for (BaseTaskWriter taskWriter : writers.values()) {
                for (DataFile dataFile : taskWriter.dataFiles()) {
                    appendFiles.appendFile(dataFile);
                }
            }
        } catch (IOException e) {
            fail = true;
            log.error("append files error, currentOffsets {}", currentOffsets, e);
        }
        // todo add kafka info snapshot.summary().put();
        if (!fail) {
            Snapshot snapshot = appendFiles.apply();
            appendFiles.commit();
            log.info("snapshot {} is commit", snapshot.snapshotId());
        }
    }

    @Override
    public String version() {
        return null;
    }

    private FileFormat getFileFormat(Map<String, String> tableProperties, Map<String, String> userProperties) {
        String tableFileFormat = tableProperties.get("write.format.default");
        String userFileFormat = userProperties.getOrDefault("kafka.iceberg.sink.table.file.format", "parquet");
        if (tableFileFormat != null) {
            return FileFormat.fromFileName(tableFileFormat);
        }
        return FileFormat.fromFileName(userFileFormat);
    }
}
