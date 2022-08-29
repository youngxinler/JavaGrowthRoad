package com.yxler;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.*;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.data.GenericAppenderFactory;
import org.apache.iceberg.data.GenericRecord;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.hadoop.HadoopCatalog;
import org.apache.iceberg.io.*;
import org.apache.iceberg.relocated.com.google.common.collect.Streams;
import org.apache.iceberg.types.Types;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTaskContext;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * @author youngxinler
 * @description TODO
 * @date 2022-07-15  23:33
 */
public class IcebergTest {

    String icebergWareHousePath = "/Users/yxler/data/iceberg";

    @Before
    public void init() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            icebergWareHousePath = "B:\\tmp\\iceberg";
        }
    }

    @Test
    public void hadoopUnPartitionTableTest() {
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Schema schema = new Schema(
                Types.NestedField.required(1, "level", Types.StringType.get()),
                Types.NestedField.required(2, "event_time", Types.LongType.get()),
                Types.NestedField.required(3, "message", Types.StringType.get())
        );
        PartitionSpec spec = PartitionSpec.unpartitioned();

        Table table = hadoopCatalog.createTable(name, schema, spec);
    }

    @Test
    public void hadoopPartitionTableTest() {
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs_partitioned");
        Schema schema = new Schema(
                Types.NestedField.required(1, "level", Types.StringType.get()),
                Types.NestedField.required(2, "event_time", Types.LongType.get()),
                Types.NestedField.required(3, "message", Types.StringType.get())
        );
        PartitionSpec spec = PartitionSpec.builderFor(schema).bucket("level", 4).build();
        Table table = hadoopCatalog.createTable(name, schema, spec);
    }

    class OwnPartitionedWrite<T extends StructLike> extends PartitionedWriter<T> {
        private PartitionKey partitionKey;

        public OwnPartitionedWrite(PartitionSpec spec, FileFormat format, FileAppenderFactory<T> appenderFactory, OutputFileFactory fileFactory, FileIO io, long targetFileSize) {
            super(spec, format, appenderFactory, fileFactory, io, targetFileSize);
            this.partitionKey = new PartitionKey(spec, spec().schema());
        }

        @Override
        public PartitionKey partition(T t) {
            this.partitionKey.partition(t);
            return this.partitionKey;
        }
    }

    @Test
    public void writePartitionedTableData() throws Exception {
        hadoopPartitionTableTest();
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs_partitioned");
        Table table = hadoopCatalog.loadTable(name);

        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, 1, 1).format(FileFormat.PARQUET).build();
        HashMap<PartitionKey, PartitionedWriter<Record>> writerMap = new HashMap<>();

        Random random = new Random();
        List<String> levels = Arrays.asList("info", "debug", "error", "warn");
        GenericRecord genericRecord = GenericRecord.create(table.schema());
        PartitionKey commonPartition = new PartitionKey(table.spec(), table.schema());

        for (int i = 0; i < 10000; i++) {
            GenericRecord record = genericRecord.copy();
            record.setField("level",  levels.get(random.nextInt(levels.size())));
            record.setField("event_time", System.currentTimeMillis());
            record.setField("message", "nice" + System.currentTimeMillis());

            commonPartition.partition(record);

            if (!writerMap.containsKey(commonPartition)) {
                PartitionKey partitionKey = commonPartition.copy();
                OwnPartitionedWrite<Record> recordPartitionedWriter = new OwnPartitionedWrite<>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);
                writerMap.put(partitionKey, recordPartitionedWriter);
            }

            writerMap.get(commonPartition).write(record);
        }


        AppendFiles appendFiles = table.newAppend();

        for (PartitionedWriter<Record> partitionedWriter : writerMap.values()) {
            Arrays.stream(partitionedWriter.dataFiles()).forEach(appendFiles::appendFile);
            partitionedWriter.close();
        }

        Snapshot res = appendFiles.apply();
        appendFiles.commit();
        System.out.println(res);
    }

    @Test
    public void deltaWritePartitionedTableData() {
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs_partitioned");
        Table table = hadoopCatalog.loadTable(name);

    }


    @Test
    public void sparkInsert() {
        SparkConf sparkConf = new SparkConf().setAppName("test").setIfMissing("spark.master", "local");
        sparkConf.set("spark.sql.catalog.hadoop_prod", "org.apache.iceberg.spark.SparkCatalog");
        sparkConf.set("spark.sql.catalog.hadoop_prod.type", "hadoop");
        sparkConf.set("spark.sql.catalog.hadoop_prod.warehouse", "hdfs://localhost:9000/iceberg");
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();
        spark.sparkContext().hadoopConfiguration().set("ipc.client.fallback-to-simple-auth-allowed", "true");

    }


    @Test
    public void testTableApi() throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("ipc.client.fallback-to-simple-auth-allowed", "true");
        String icebergWareHousePath = "hdfs://localhost:9000/iceberg";
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        Iterable<Snapshot> snapshots = table.snapshots();
        System.out.println(snapshots);
        for (Snapshot snapshot : snapshots) {
            List<ManifestFile> manifestFiles = snapshot.allManifests();
            Iterable<DataFile> dataFiles = snapshot.addedFiles();

            for (DataFile dataFile : dataFiles) {
                System.out.println(dataFile);
            }
            for (ManifestFile manifestFile : manifestFiles) {
                ManifestReader<DataFile> reader = ManifestFiles.read(manifestFile, table.io());
                List<String> files = Streams.stream(reader).map(f -> f.path().toString()).collect(Collectors.toList());
                System.out.println(manifestFile);
            }
        }
    }

    private static final long TARGET_FILE_SIZE_IN_BYTES = 50L * 1024 * 1024;

    @Test
    public void insertData() throws Exception {
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, 1, 1).format(FileFormat.PARQUET).build();
        UnpartitionedWriter<Record> unpartitionedWriter = new UnpartitionedWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);
        GenericRecord genericRecord = GenericRecord.create(table.schema());
        for (int i = 0; i < 10000; i++) {
            genericRecord.setField("level", "info" + System.currentTimeMillis());
            genericRecord.setField("event_time", System.currentTimeMillis());
            genericRecord.setField("message", "nice" + System.currentTimeMillis());
            unpartitionedWriter.write(genericRecord);
        }
        unpartitionedWriter.close();

        AppendFiles appendFiles = table.newAppend();
        for (DataFile dataFile : unpartitionedWriter.dataFiles()) {
            appendFiles.appendFile(dataFile);
        }
        Snapshot res = appendFiles.apply();
        appendFiles.commit();
        System.out.println(res);
    }

    @Test
    public void test() {

    }
}

