package com.study.demo.taotao_seller_service.utils.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.jms.Destination;


@Component
@PropertySource(value = "classpath:activemq.properties")
public class ActiveMqConfig {

    @Value("${queueName}")
    private String queueName;

    @Value("${deleteQueueName}")
    private String deleteQueueName;

    @Bean("queue")
    public Destination queue(){
        return new ActiveMQQueue(queueName);
    }

    @Bean("deleteSolrQueue")
    public Destination deleteSolrQueue(){
        return new ActiveMQQueue(deleteQueueName);
    }
}
