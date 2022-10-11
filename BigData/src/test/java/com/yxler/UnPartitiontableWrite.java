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
//import org.apache.iceberg.io.UnpartitionedWriter;
//import org.junit.Test;
//
//public class UnPartitiontableWrite {
//
//    @Test
//    public void testWrite() throws Exception{
//        Configuration configuration = new Configuration();
//        Catalog catalog = new HadoopCatalog(configuration, icebergWareHousePath);
//
//        // Load table from catalog
//        TableIdentifier name = TableIdentifier.of("logging", "logs");
//        Table table = catalog.loadTable(name);
//
//        // Use GenericAppendFactory to create record for write
//        GenericAppenderFactory appenderFactory = new GenericAppenderFactory(table.schema());
//
//        // Use OutputFileFactory to get OutputFile
//        int partitionId = 1, taskId = 1;
//        OutputFileFactory outputFileFactory = OutputFileFactory.builderFor(table, partitionId, taskId).format(FileFormat.PARQUET).build();
//
//
//        UnpartitionedWriter<Record> unpartitionedWriter = new UnpartitionedWriter<Record>(table.spec(), FileFormat.PARQUET, appenderFactory, outputFileFactory, table.io(), TARGET_FILE_SIZE_IN_BYTES);
//        GenericRecord genericRecord = GenericRecord.create(table.schema());
//
//        // assume write 100 records
//        for (int i = 0; i < 100; i++) {
//            genericRecord.setField("level", "info");
//            genericRecord.setField("event_time", System.currentTimeMillis());
//            genericRecord.setField("message", "Iceberg is a great table format");
//            unpartitionedWriter.write(genericRecord.copy());
//        }
//
//        // submit datafiles to the table
//        AppendFiles appendFiles = table.newAppend();
//        for (DataFile dataFile : unpartitionedWriter.dataFiles()) {
//            appendFiles.appendFile(dataFile);
//        }
//
//        // submit snapshot
//        Snapshot newSnapshot = appendFiles.apply();
//        appendFiles.commit();
//    }
//}
