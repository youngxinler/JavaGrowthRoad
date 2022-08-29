package com.yxler;


import org.apache.iceberg.FileFormat;
import org.apache.iceberg.PartitionSpec;
import org.apache.iceberg.io.FileAppenderFactory;
import org.apache.iceberg.io.FileIO;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.io.UnpartitionedWriter;

class KafkaUnpartitionedWriter<T> extends UnpartitionedWriter<T> {
    public KafkaUnpartitionedWriter(PartitionSpec spec, FileFormat format, FileAppenderFactory<T> appenderFactory, OutputFileFactory fileFactory, FileIO io, long targetFileSize) {
        super(spec, format, appenderFactory, fileFactory, io, targetFileSize);
    }
}
