package com.wsbo.broker.controller;

import com.wsbo.broker.core.Kmq;
import com.wsbo.broker.core.KmqBroker;
import com.wsbo.broker.core.KmqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author: 项峥
 * @Date: 2021/4/18 21:11
 */
@RestController
@RequestMapping("/kmq")
public class KmqBrokerController {
    @Autowired
    private KmqBroker kmqBroker;

    @PostMapping("/topic/{topicName}/message")
    public ResponseData<Void> sendMessage(@PathVariable String topicName, @RequestBody MessageRequest messageRequest) {
        Kmq kmq = Optional.ofNullable(kmqBroker.findKmq(topicName)).orElseThrow(() -> new RuntimeException("topicName not exist"));
        kmq.send(new KmqMessage(messageRequest.getMessage()));
        return ResponseData.success();
    }

    @PostMapping("/topic/{topicName}")
    public ResponseData<Void> createTopic(@PathVariable String topicName) {
        kmqBroker.createTopic(topicName);
        return ResponseData.success();
    }

    @GetMapping("/topic/{topicName}/message")
    public ResponseData<MessageDTO> getMessage(@PathVariable String topicName, String consumerId) {
        Kmq<String> kmq = Optional.ofNullable(kmqBroker.findKmq(topicName)).orElseThrow(() -> new RuntimeException("topicName not exist"));
        KmqMessage<String> message = kmq.read(consumerId);
        return ResponseData.success(Optional.ofNullable(message).map(m -> new MessageDTO(m.getBody(), m.getCursor())).orElse(null));
    }

    @PostMapping("topic/{topicName}/commit")
    public ResponseData<Void> commit(@PathVariable String topicName, @RequestBody CommitRequest commitRequest) {
        Kmq<String> kmq = Optional.ofNullable(kmqBroker.findKmq(topicName)).orElseThrow(() -> new RuntimeException("topicName not exist"));
        kmq.commit(commitRequest.getConsumerId(), commitRequest.getReadCursor());
        return ResponseData.success();
    }
}
