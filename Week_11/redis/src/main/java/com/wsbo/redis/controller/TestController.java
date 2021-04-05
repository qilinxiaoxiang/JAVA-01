package com.wsbo.redis.controller;

import com.wsbo.redis.counter.DistributedCounter;
import com.wsbo.redis.lock.DistributedLock;
import com.wsbo.redis.mq.Message;
import com.wsbo.redis.mq.MyMessageListener;
import com.wsbo.redis.mq.MyStringMessageListener;
import org.redisson.api.RLock;
import org.redisson.api.RTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 项峥
 * @Date: 2021/4/5 17:34
 */
@RestController
public class TestController {
    public static final String LOCK_KEY = "LOCK_KEY";
    public static final String COUNTER_KEY = "COUNTER_KEY";
    @Autowired
    private DistributedLock distributedLock;
    @Autowired
    private DistributedCounter distributedCounter;
    @Autowired
    @Qualifier("stringTopic")
    private RTopic stringTopic;

    @GetMapping("lock")
    public String lock() throws InterruptedException {
        RLock lock = distributedLock.getLock(LOCK_KEY);
        System.out.println(Thread.currentThread().getId());
        boolean success = lock.tryLock(1, 5, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getId());
        if (success) {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getId());
                return "lock success";
            } finally {
                System.out.println(Thread.currentThread().getId());
                lock.unlock();
            }
        } else {
            return "lock failed";
        }
    }

    @GetMapping("initCounter")
    public String initCounter(long count) {
        distributedCounter.set(COUNTER_KEY, count);
        return "success";
    }

    @GetMapping("decrement")
    public String decrement() throws InterruptedException {
        Thread.sleep(3000);
        return distributedCounter.decrement(COUNTER_KEY) ? "success" : "failed";
    }

    @GetMapping("publish")
    public String publish(String id) {
        stringTopic.publish(id);
        return "success";
    }
}
