package com.yxler;

import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.*;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.data.GenericAppenderFactory;
import org.apache.iceberg.data.GenericRecord;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.hadoop.HadoopCatalog;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.io.UnpartitionedWriter;
import org.apache.iceberg.relocated.com.google.common.collect.Streams;
import org.apache.iceberg.types.Types;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author youngxinler
 * @description TODO
 * @date 2022-07-15  23:33
 */
public class IcebergTest {

    @Test
    public void hadoopTableTest() {
        Configuration configuration = new Configuration();
        configuration.set("ipc.client.fallback-to-simple-auth-allowed", "true");
//        String icebergWareHousePath = "hdfs://localhost:9000/iceberg";
        String icebergWareHousePath = "/home/yxler/Desktop/data/iceberg";
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
        configuration.set("ipc.client.fallback-to-simple-auth-allowed", "true");
        String icebergWareHousePath = "hdfs://localhost:9000/iceberg";
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, 1, 1).format(FileFormat.PARQUET).build();
        UnpartitionedWriter<Record> unpartitionedWriter = new UnpartitionedWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);
        GenericRecord genericRecord = GenericRecord.create(table.schema());
        genericRecord.setField("level", "info");
        genericRecord.setField("event_time", System.currentTimeMillis());
        genericRecord.setField("message", "nice");
        unpartitionedWriter.write(genericRecord);
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
