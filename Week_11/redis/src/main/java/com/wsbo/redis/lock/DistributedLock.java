package com.wsbo.redis.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @Author: 项峥
 * @Date: 2021/4/5 17:16
 */
@Component
public class DistributedLock{
    @Autowired
    private  RedissonClient redissonClient;

    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }
}
