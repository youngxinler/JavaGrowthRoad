package com.yxler;

import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.CatalogUtil;
import org.apache.iceberg.Table;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class IcebergSinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(IcebergSinkTask.class);
    private Map<String, String> properties;
    private OutputFileFactory outputFileFactory;

    public IcebergSinkTask() {
    }

    @Override
    public void start(Map<String, String> props) {
        this.properties = props;
        String catalogName = properties.get("kafka.iceberg.catalog.name");
        String catalogImpl = properties.get("kafka.iceberg.catalog.impl");
        String tableIdentify = properties.get("kafka.iceberg.sink.table");
        Configuration hadoopConfiguration = new Configuration();
        Catalog catalog = CatalogUtil.loadCatalog(catalogImpl, catalogName, props, hadoopConfiguration);
        TableIdentifier tableIdentifier = TableIdentifier.of(tableIdentify);
        Table table = catalog.loadTable(tableIdentifier);
        if (table == null) {
            throw new IllegalArgumentException(String.format("iceberg table {} not exist", tableIdentify));
        }

        Set<TopicPartition> assignedPartition = this.context.assignment();
        assignedPartition.stream().map(x -> x.partition()).
                outputFileFactory = OutputFileFactory.builderFor(table, )
    }

    @Override
    public void put(Collection<SinkRecord> collection) {

    }

    @Override
    public void stop() {

    }


    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
        super.flush(currentOffsets);
    }

    @Override
    public String version() {
        return null;
    }
}
