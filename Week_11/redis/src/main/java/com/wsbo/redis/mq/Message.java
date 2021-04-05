package com.wsbo.redis.mq;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: 项峥
 * @Date: 2021/4/6 1:54
 */
@Data
@Builder
public class Message {
    private String id;
}
