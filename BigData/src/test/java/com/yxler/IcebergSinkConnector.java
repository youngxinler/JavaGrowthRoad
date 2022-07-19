package com.yxler;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcebergSinkConnector extends SinkConnector {
    private static final Logger log = LoggerFactory.getLogger(IcebergSinkConnector.class);
    private Map<String, String> properties;


    @Override
    public void start(Map<String, String> props) {
        this.properties = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return IcebergSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int taskNum) {
        List<Map<String, String>> taskProperties = new ArrayList<>(taskNum);
        Map<String, String> taskOwnProperty = new HashMap<>(properties);

        for (int i = 0; i < taskNum; i++) {
            taskProperties.add(taskOwnProperty);
        }
        return taskProperties;
    }

    @Override
    public void stop() {
        log.info("kafka iceberg sink stop");
    }

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public String version() {
        return properties.getOrDefault("kafka.iceberg.sink.version", "1.0.0");
    }
}
