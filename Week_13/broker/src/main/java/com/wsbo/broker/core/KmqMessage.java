package com.wsbo.broker.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
public class KmqMessage<T> {

    private HashMap<String,Object> headers;

    private T body;

    private int cursor;

    public KmqMessage() {
    }

    public KmqMessage(T body) {
        this.body = body;
    }

    public KmqMessage(T body, int cursor) {
        this.body = body;
        this.cursor = cursor;
    }
}
