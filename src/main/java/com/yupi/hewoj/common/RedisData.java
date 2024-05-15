package com.yupi.hewoj.common;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class RedisData {
    private Object data;
    /**
     * 逻辑过期时间
     *
     */
    private LocalDateTime expireTime;

}
