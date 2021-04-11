package com.wsbo.activemqtest;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 * @Author: 项峥
 * @Date: 2021/4/11 16:04
 */
@Component
public class MyListener {
    @JmsListener(destination = "testQueue")
    public void onMessage(Message message) {
        System.out.println("收到的信息：" + message);
    }
}
