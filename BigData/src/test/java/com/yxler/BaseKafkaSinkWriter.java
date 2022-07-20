package com.yxler;

import org.apache.iceberg.*;
import org.apache.iceberg.io.BaseTaskWriter;
import org.apache.iceberg.io.FileAppenderFactory;
import org.apache.iceberg.io.FileIO;
import org.apache.iceberg.io.OutputFileFactory;
import org.apache.iceberg.data.Record;

import java.io.IOException;

abstract class BaseKafkaSinkWriter extends BaseTaskWriter<Record> {


    protected BaseKafkaSinkWriter(PartitionSpec spec, FileFormat format, FileAppenderFactory<Record> appenderFactory,
                                  OutputFileFactory fileFactory, FileIO io, long targetFileSize) {
        super(spec, format, appenderFactory, fileFactory, io, targetFileSize);
    }

    abstract RecordWriter getWriter(Record record);

    @Override
    public void write(Record record) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }


    protected class RecordWriter extends BaseEqualityDeltaWriter {
        protected RecordWriter(StructLike partition, Schema schema, Schema deleteSchema) {
            super(partition, schema, deleteSchema);
        }

        @Override
        protected StructLike asStructLike(Record record) {
            return record;
        }
    }
}
