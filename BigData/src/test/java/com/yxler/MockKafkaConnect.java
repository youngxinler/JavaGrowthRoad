package com.yxler;

import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkTaskContext;

import java.util.Map;
import java.util.Set;


public class MockKafkaConnect implements SinkTaskContext{
    private final TopicPartition topicPartition;
    private Map<String, String> connectConfigs;


    public MockKafkaConnect(TopicPartition topicPartition, Map<String, String> connectConfigs) {
        this.topicPartition = topicPartition;
        this.connectConfigs = connectConfigs;
    }

    @Override
    public Map<String, String> configs() {
        return this.connectConfigs;
    }

    @Override
    public void offset(Map<TopicPartition, Long> offsets) {

    }

    @Override
    public void offset(TopicPartition tp, long offset) {

    }

    @Override
    public void timeout(long timeoutMs) {

    }

    @Override
    public Set<TopicPartition> assignment() {
        return null;
    }

    @Override
    public void pause(TopicPartition... partitions) {

    }

    @Override
    public void resume(TopicPartition... partitions) {

    }

    @Override
    public void requestCommit() {

    }
}
