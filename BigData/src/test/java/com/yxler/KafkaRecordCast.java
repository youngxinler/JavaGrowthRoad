package com.yxler;

import org.apache.iceberg.Schema;
import org.apache.iceberg.data.Record;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.sink.SinkRecord;

@FunctionalInterface
public interface KafkaRecordCast {
    Record kafkaRecord2IcebergRecord(SinkRecord kafkaRecord, Schema icebergSchema);
}


class DefaultKafkaRecordCast implements KafkaRecordCast {
    @Override
    public Record kafkaRecord2IcebergRecord(SinkRecord kafkaRecord, Schema icebergSchema) {
        org.apache.kafka.connect.data.Schema kafkaValueSchema = kafkaRecord.valueSchema();
        kafkaValueSchema.fields().get(0).schema().type();
        kafkaRecord.value();
        Field field = kafkaValueSchema.fields().get(0);

    }
}
