package com.wsbo.redis.mq;

import org.redisson.api.listener.MessageListener;

/**
 * @Author: 项峥
 * @Date: 2021/4/6 1:59
 */
public class MyMessageListener implements MessageListener<Message> {
    @Override
    public void onMessage(CharSequence channel, Message msg) {
        System.out.println(msg.getId());
    }
}
