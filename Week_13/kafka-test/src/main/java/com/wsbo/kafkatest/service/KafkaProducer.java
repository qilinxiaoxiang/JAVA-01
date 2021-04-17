package com.wsbo.kafkatest.service;

import com.wsbo.kafkatest.config.KafkaTopicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: 项峥
 * @Date: 2021/4/17 23:36
 */
@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("produce message:" + message);
        kafkaTemplate.send(KafkaTopicConfiguration.TOPIC, message);
    }
}
