package com.yxler;

import org.apache.iceberg.Schema;
import org.apache.iceberg.data.GenericRecord;
import org.apache.iceberg.data.Record;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Values;
import org.apache.kafka.connect.sink.SinkRecord;

import java.util.List;

import static org.apache.kafka.connect.data.Schema.Type.STRUCT;

@FunctionalInterface
public interface KafkaRecordCast {
    Record kafkaRecord2IcebergRecord(SinkRecord kafkaRecord, Schema icebergSchema);
}


class DefaultKafkaRecordCast implements KafkaRecordCast {

    private final Schema icebergSchema;
    private final GenericRecord genericRecord;

    public DefaultKafkaRecordCast(Schema icebergSchema) {
        this.icebergSchema = icebergSchema;
        genericRecord = GenericRecord.create(icebergSchema);
    }

    @Override
    public Record kafkaRecord2IcebergRecord(SinkRecord kafkaRecord, Schema icebergSchema) {
        Object value = kafkaRecord.value();
        org.apache.kafka.connect.data.Schema vSchema = kafkaRecord.valueSchema();

        if (vSchema.type() == STRUCT) {
            GenericRecord record = genericRecord.copy();
            visitField(record, vSchema, Values.convertToStruct(vSchema, value));
            return record;
        } else {
            throw new UnsupportedOperationException("can't cast type without fields");
        }
    }


    private void visitField(GenericRecord genericRecord, org.apache.kafka.connect.data.Schema kafkaSchema, Struct value) {
        List<Field> fields = kafkaSchema.fields();
        for (int i = 0; i < fields.size(); i++) {
//            Field field = fields.get(i);
//            switch (field.schema().type()) {
//                case INT8:
//                case INT16:
//                case INT32:
//                case INT64:
//                case FLOAT32:
//                case FLOAT64:
//                case BOOLEAN:
//                case STRING:
//                case BYTES:
//                case ARRAY:
//                case MAP:
//                    genericRecord.setField(field.name(), value.get(field));
//                    break;
//                case STRUCT:
//
//            }
            Field field = fields.get(i);
            genericRecord.setField(field.name(), value.get(field));
        }

    }

}
