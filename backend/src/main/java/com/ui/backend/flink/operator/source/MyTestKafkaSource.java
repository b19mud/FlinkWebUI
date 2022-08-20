package com.ui.backend.flink.operator.source;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import java.util.Properties;

public class MyTestKafkaSource extends FlinkKafkaConsumer010<String> {
    public MyTestKafkaSource(String topic, KafkaDeserializationSchema<String> deserializer, Properties props) {
        super(topic, deserializer, props);
    }
}
