package com.wsbo.kafkatest.service;

import com.wsbo.kafkatest.config.KafkaTopicConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @Author: 项峥
 * @Date: 2021/4/17 23:39
 */
@Service
public class KafkaConsumer {
    @KafkaListener(topics = KafkaTopicConfiguration.TOPIC, groupId = "group_id")
    public void consumeMessage(String message) {
        System.out.println("consume message:" + message);
    }
}
