package com.wsbo.activemqtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: 项峥
 * @Date: 2021/4/11 16:23
 */
@Component
public class MyProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.send("testQueue", session -> session.createObjectMessage(message));
    }
}
