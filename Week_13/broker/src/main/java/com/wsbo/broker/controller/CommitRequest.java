package com.wsbo.broker.controller;

import lombok.Data;

/**
 * @Author: 项峥
 * @Date: 2021/4/18 22:31
 */
@Data
public class CommitRequest {
    private String consumerId;
    private Integer readCursor;
}
