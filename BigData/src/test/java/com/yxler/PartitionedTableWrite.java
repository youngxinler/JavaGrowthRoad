//package com.yxler;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.iceberg.*;
//import org.apache.iceberg.catalog.Catalog;
//import org.apache.iceberg.catalog.TableIdentifier;
//import org.apache.iceberg.data.GenericAppenderFactory;
//import org.apache.iceberg.data.GenericRecord;
//import org.apache.iceberg.data.Record;
//import org.apache.iceberg.hadoop.HadoopCatalog;
//import org.apache.iceberg.io.OutputFileFactory;
//import org.apache.iceberg.io.PartitionedFanoutWriter;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class PartitionedTableWrite {
//
//    @Test
//    public void testWrite() throws Exception{
//        Configuration configuration = new Configuration();
//        Catalog hadoopCatalog = new HadoopCatalog(configuration, icebergWareHousePath);
//        // Load table from catalog
//        TableIdentifier name = TableIdentifier.of("logging", "logs_partitioned");
//        Table table = hadoopCatalog.loadTable(name);
//
//
//        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
//
//        int partitionId = 1, taskId = 1;
//        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, partitionId, taskId).format(FileFormat.PARQUET).build();
//        final PartitionKey partitionKey = new PartitionKey(table.spec(), table.spec().schema());
//
//        // partitionedFanoutWriter will auto partitioned record and create the partitioned writer
//        PartitionedFanoutWriter<Record> partitionedFanoutWriter = new PartitionedFanoutWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES) {
//            @Override
//            protected PartitionKey partition(Record record) {
//                partitionKey.partition(record);
//                return partitionKey;
//            }
//        };
//
//        Random random = new Random();
//        List<String> levels = Arrays.asList("info", "debug", "error", "warn");
//        GenericRecord genericRecord = GenericRecord.create(table.schema());
//
//        // assume write 1000 records
//        for (int i = 0; i < 1000; i++) {
//            GenericRecord record = genericRecord.copy();
//            record.setField("level",  levels.get(random.nextInt(levels.size())));
//            record.setField("event_time", System.currentTimeMillis());
//            record.setField("message", "Iceberg is a great table format");
//            record.setField("call_stack", "stack");
//            partitionedFanoutWriter.write(record);
//        }
//
//
//        AppendFiles appendFiles = table.newAppend();
//
//        // submit datafiles to the table
//        Arrays.stream(partitionedFanoutWriter.dataFiles()).forEach(appendFiles::appendFile);
//
//        // submit snapshot
//        Snapshot newSnapshot = appendFiles.apply();
//        appendFiles.commit();
//    }
//}
