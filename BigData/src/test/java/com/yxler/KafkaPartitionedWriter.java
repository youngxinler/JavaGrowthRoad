package com.yxler;

import org.apache.iceberg.FileFormat;
import org.apache.iceberg.PartitionKey;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.Schema;
import org.apache.iceberg.data.Record;
import org.apache.iceberg.io.FileAppenderFactory;
import org.apache.iceberg.io.FileIO;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.io.PartitionedWriter;

public class KafkaPartitionedWriter<T> extends PartitionedWriter<T> {

    private Schema schema;
    private PartitionKey partitionKey;

    protected KafkaPartitionedWriter(PartitionSpec spec, FileFormat format, FileAppenderFactory appenderFactory, OutputFileFactory fileFactory, FileIO io, long targetFileSize, Schema schema) {
        super(spec, format, appenderFactory, fileFactory, io, targetFileSize);
        this.schema = schema;
        this.partitionKey = new PartitionKey(spec, schema);
    }

    @Override
    protected PartitionKey partition(T record) {
        return this.partitionKey;
    }
}
