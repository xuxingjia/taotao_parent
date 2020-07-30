package com.study.demo.taotao_user_service.utils.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@PropertySource(value = "classpath:config.properties")
public class SmsActiveQueue {

    @Value("${SMS_QUEUE}")
    private String smsQueue;

    @Bean
    public Destination smsQueue(){
        return new ActiveMQQueue(smsQueue);
    }
}
