package com.wsbo.kafkatest.controller;

import com.wsbo.kafkatest.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 项峥
 * @Date: 2021/4/17 23:41
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaProducer producer;

    @RequestMapping("/message")
    public void sendMessage(String message) {
        producer.sendMessage(message);
    }
}
