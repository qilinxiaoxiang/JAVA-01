package com.wsbo.kafkatest.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @Author: 项峥
 * @Date: 2021/4/17 23:33
 */
@Configuration
public class KafkaTopicConfiguration {
    public static final String TOPIC = "my-first-kafka-topic";
    @Bean
    public NewTopic topicExample() {
        return TopicBuilder.name(TOPIC)
                .partitions(3)
                .replicas(2)
                .build();
    }
}
