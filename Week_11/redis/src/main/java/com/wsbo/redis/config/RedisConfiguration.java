package com.wsbo.redis.config;

import com.wsbo.redis.mq.Message;
import com.wsbo.redis.mq.MyMessageListener;
import com.wsbo.redis.mq.MyStringMessageListener;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 项峥
 * @Date: 2021/4/6 1:20
 */
@Configuration
public class RedisConfiguration {
    public static final String MESSAGE_TOPIC = "MESSAGE_TOPIC";
    public static final String STRING_TOPIC = "STRING_TOPIC";
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setConnectionMinimumIdleSize(100)
                .setConnectionPoolSize(1000)
                .setConnectTimeout(5000);
        return Redisson.create(config);
    }

    @Bean
    public RTopic messageTopic(RedissonClient redissonClient) {
        RTopic messageTopic = redissonClient.getTopic(MESSAGE_TOPIC);
        messageTopic.addListener(Message.class, new MyMessageListener());
        return messageTopic;
    }

    @Bean
    public RTopic stringTopic(RedissonClient redissonClient) {
        RTopic messageTopic = redissonClient.getTopic(STRING_TOPIC);
        messageTopic.addListener(String.class, new MyStringMessageListener());
        return messageTopic;
    }
}
