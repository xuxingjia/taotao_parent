package com.study.demo.taotao_search_service.utils.activemq;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Service
@PropertySource(value = "activemq.properties")
public class ActiveMqConfig {

    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;


}
