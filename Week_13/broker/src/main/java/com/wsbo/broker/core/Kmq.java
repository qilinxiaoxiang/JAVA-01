package com.wsbo.broker.core;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Data
public final class Kmq<T> {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
        this.readCursorMap = new ConcurrentHashMap<>();
    }

    private String topic;

    private int writePosition;

    private ConcurrentHashMap<String, Integer> readCursorMap;

    private int capacity;

    private KmqMessage<T>[] queue;

    public synchronized void send(KmqMessage<T> message) {
        message.setCursor(writePosition);
        queue[writePosition] = message;
        writePosition++;
    }

    public KmqMessage<T> read(String consumerId) {
        int readCursor = 0;
        if (!readCursorMap.containsKey(consumerId)) {
            readCursorMap.put(consumerId, readCursor);
        } else {
            readCursor = readCursorMap.get(consumerId);
        }
        if (readCursor < writePosition) {
            return queue[readCursor];
        }
        return null;
    }

    public void commit(String consumerId, int readCursor) {
        if (readCursorMap.containsKey(consumerId)) {
            readCursorMap.put(consumerId, readCursor + 1);
        }
    }
}
