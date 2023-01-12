package com.yxler;

import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.*;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.data.*;
import org.apache.iceberg.expressions.Expression;
import org.apache.iceberg.expressions.Expressions;
import org.apache.iceberg.hadoop.HadoopCatalog;
import org.apache.iceberg.io.*;
import org.apache.iceberg.relocated.com.google.common.collect.Streams;
import org.apache.iceberg.types.Types;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.parquet.schema.LogicalTypeAnnotation;
import org.apache.parquet.hadoop.ColumnChunkPageWriteStore;
import org.junit.Before;
import org.junit.Test;
import org.apache.avro.LogicalTypes;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author youngxinler
 * @description TODO
 * @date 2022-07-15  23:33
 */
public class IcebergTest {
    @Before
    public void init() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            icebergWareHousePath = "B:\\tmp\\iceberg";
        }
    }


    String icebergWareHousePath = "/Users/yxler/data/iceberg";


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
    public void hadoopPartitionTableCreateTest() {
        Configuration configuration = new Configuration();
        HadoopCatalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Schema schema = new Schema(
                Types.NestedField.required(1, "level", Types.StringType.get()),
                Types.NestedField.required(2, "event_time", Types.TimestampType.withZone()),
                Types.NestedField.required(3, "message", Types.StringType.get()),
                Types.NestedField.optional(4, "call_stack", Types.ListType.ofRequired(5, Types.StringType.get()))
        );
        PartitionSpec spec = PartitionSpec.builderFor(schema)
                .hour("event_time")
                .identity("level")
                .build();
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
    public void testPartitionedKey() throws Exception{
        Configuration configuration = new Configuration();
        Catalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        // Load table from catalog
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);
        PartitionSpec spec = table.spec();
        final PartitionKey partitionKey = new PartitionKey(table.spec(), table.schema());

        GenericRecord genericRecord = GenericRecord.create(table.schema());
        GenericRecord record = genericRecord.copy();
        Random random = new Random();
        List<String> levels = Arrays.asList("info", "debug", "error", "warn");
        record.setField("level",  levels.get(random.nextInt(levels.size())));
//        record.setField("event_time", System.currentTimeMillis());
//        record.setField("event_time", java.time.OffsetDateTime.now());
        record.setField("event_time", new java.sql.Timestamp(new Date().getTime()));
        record.setField("message", "Iceberg is a great table format");
        record.setField("call_stack", Arrays.asList("NullPointerException"));

        partitionKey.partition(record);

        System.out.println(partitionKey);
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
    public void insertUnPartitionTableData() throws Exception {
        // 通过Hadoop Catalogs 初始化"logs"的table对象
        Configuration configuration = new Configuration();
        Catalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        // GenricAppenderFactory是iceberg的一条Record(数据表中的一行)的工厂;
        // 可通过该工厂创建Record
        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
        GenericRecord genericRecord = GenericRecord.create(table.schema());

        // iceberg表在写入数据的时候, 通过OutputFileFactory来获取要写入的数据文件, 该文件路径通过生成表设置的warehouse路径和分区字段来生成, 通过文件路径来调用底层的FileIO来获取写入文件的OutputFile对象.
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, 1, 1).format(FileFormat.PARQUET).build();

        // 非分区表直接生成一个写入的Writer的就可以了, 这里官方Api有具体实现, 直接调用就可以.
        UnpartitionedWriter<Record> unpartitionedWriter = new UnpartitionedWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);

        // 写入一万条数据.
        for (int i = 0; i < 10000; i++) {
            genericRecord.setField("level", "info" + System.currentTimeMillis());
            genericRecord.setField("event_time", System.currentTimeMillis());
            genericRecord.setField("message", "nice" + System.currentTimeMillis());
            unpartitionedWriter.write(genericRecord.copy());
        }
        unpartitionedWriter.close();


        // 以上写入数据文件之后, 通过Table的Api对写入的数据文件提交到表的元数据中.
        AppendFiles appendFiles = table.newAppend();
        for (DataFile dataFile : unpartitionedWriter.dataFiles()) {
            appendFiles.appendFile(dataFile);
        }
        // 提交的文件生成一个snapshot并进行提交.
        Snapshot res = appendFiles.apply();
        appendFiles.commit();
    }

    @Test
    public void testDoc() throws Exception{
        Configuration configuration = new Configuration();
        Catalog catalog = new HadoopCatalog(configuration, icebergWareHousePath);

        // Load table from catalog
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = catalog.loadTable(name);

        // Use GenericAppendFactory to create record for write
        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());

        // Use OutputFileFactory to get OutputFile
        int partitionId = 1, taskId = 1;
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, partitionId, taskId).format(FileFormat.PARQUET).build();


        UnpartitionedWriter<Record> unpartitionedWriter = new UnpartitionedWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);
        GenericRecord genericRecord = GenericRecord.create(table.schema());

        // assume write 100 records
        for (int i = 0; i < 100; i++) {
            genericRecord.setField("level", "info");
            genericRecord.setField("event_time", System.currentTimeMillis());
            genericRecord.setField("message", "Iceberg is a great table format");
            unpartitionedWriter.write(genericRecord.copy());
        }

        Transaction transaction = table.newTransaction();

        // submit datafiles to the table
        AppendFiles appendFiles = transaction.newAppend();
        for (DataFile dataFile : unpartitionedWriter.dataFiles()) {
           appendFiles.appendFile(dataFile);
        }
        transaction.commitTransaction();
    }

    @Test
    public void writePartitionedTableData() throws Exception {
        hadoopPartitionTableCreateTest();
        Configuration configuration = new Configuration();
        Catalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        // Load table from catalog
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());

        int partitionId = 1, taskId = 1;
        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, partitionId, taskId).format(FileFormat.PARQUET).build();
        final PartitionKey partitionKey = new PartitionKey(table.spec(), table.spec().schema());
        final InternalRecordWrapper recordWrapper = new InternalRecordWrapper(table.schema().asStruct());

// partitionedFanoutWriter will auto partitioned record and create the partitioned writer
        PartitionedFanoutWriter<Record> partitionedFanoutWriter = new PartitionedFanoutWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES) {
            @Override
            protected PartitionKey partition(Record record) {
                partitionKey.partition(recordWrapper.wrap(record));
                return partitionKey;
            }
        };

        GenericRecord genericRecord = GenericRecord.create(table.schema());

// assume write 1000 records
        for (int i = 0; i < 1000; i++) {
            GenericRecord record = genericRecord.copy();
            record.setField("level",  i % 6 == 0 ? "error" : "info");
            record.setField("event_time", OffsetDateTime.now());
            record.setField("message", "Iceberg is a great table format");
            record.setField("call_stack", Collections.singletonList("NullPointerException"));
            partitionedFanoutWriter.write(record);
        }
// after the data file is written above,
// the written data file is submitted to the metadata of the table through Table's Api.
        AppendFiles appendFiles = table.newAppend();
        for (DataFile dataFile : partitionedFanoutWriter.dataFiles()) {
            appendFiles.appendFile(dataFile);
        }
// The submitted file generates a snapshot and submit it.
        Snapshot res = appendFiles.apply();
        appendFiles.commit();
    }

    @Test
    public void testRead() throws IOException {
//        hadoopPartitionTableCreateTest();
        Configuration configuration = new Configuration();
        Catalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
        // Load table from catalog
        TableIdentifier name = TableIdentifier.of("logging", "logs");
        Table table = hadoopCatalog.loadTable(name);

        IcebergGenerics.ScanBuilder scanBuilder = IcebergGenerics.read(table);
        CloseableIterator<Record> records = scanBuilder.where(Expressions.equal("level", "error")).build().iterator();
        while (records.hasNext()) {
            Record record = records.next();
            // do something with record
            System.out.println(record);
        }
        records.close();
    }
}

