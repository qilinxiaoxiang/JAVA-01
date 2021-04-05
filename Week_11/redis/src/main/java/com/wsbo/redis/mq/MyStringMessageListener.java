package com.wsbo.redis.mq;

import org.redisson.api.listener.MessageListener;

/**
 * @Author: 项峥
 * @Date: 2021/4/6 2:20
 */
public class MyStringMessageListener implements MessageListener<String> {
    @Override
    public void onMessage(CharSequence channel, String msg) {
        System.out.println(msg);
    }
}
