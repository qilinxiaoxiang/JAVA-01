package com.wsbo.activemqtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 项峥
 * @Date: 2021/4/11 16:05
 */
@RestController
public class JmsController {
    @Autowired
    private MyProducer myProducer;

    @RequestMapping("queue/{content}")
    public String send2Queue(@PathVariable String content) {
        myProducer.send(content);
        return "success";
    }
}
