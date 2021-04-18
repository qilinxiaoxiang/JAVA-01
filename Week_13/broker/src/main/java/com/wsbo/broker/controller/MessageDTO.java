package com.wsbo.broker.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: 项峥
 * @Date: 2021/4/18 22:33
 */
@Data
@AllArgsConstructor
public class MessageDTO {
    private String message;
    private Integer readCursor;
}
