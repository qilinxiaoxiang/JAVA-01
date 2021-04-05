package com.wsbo.redis.counter;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 项峥
 * @Date: 2021/4/6 1:18
 */
@Component
public class DistributedCounter {
    @Autowired
    private RedissonClient redissonClient;

    public void set(String key, long count) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        atomicLong.set(count);
    }

    public boolean decrement(String key) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
        long result = atomicLong.decrementAndGet();
        return result > 0;
    }
}
