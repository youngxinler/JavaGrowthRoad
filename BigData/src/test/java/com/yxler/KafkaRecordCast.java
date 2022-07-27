package com.yxler;

import org.apache.iceberg.Schema;
import org.apache.iceberg.data.Record;
import org.apache.kafka.connect.sink.SinkRecord;

@FunctionalInterface
public interface KafkaRecordCast {
    Record kafkaRecord2IcebergRecord(SinkRecord kafkaRecord, Schema icebergSchema);
}



