package com.yupi.hewoj.utils;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 全局id生成器
 */
@Component
public class RedisIdWorker {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 基准时间
     */
    private static final long BEGIN_TIMESTAMP=1704067200L;

    /**
     *
     * @param keyPrefix:表示业务前缀
     * @param statisticalUnit：表示统计单位是一天一统计还是一月一统计
     * @return
     */
    public long nextId(String keyPrefix,String statisticalUnit){
        //1. 生成31位时间搓
        LocalDateTime now=LocalDateTime.now();
        long now_timestamp=now.toEpochSecond(ZoneOffset.UTC);
        long time_timestamp=now_timestamp-BEGIN_TIMESTAMP;

        //2.自增生成32位序列号
        long count=stringRedisTemplate.opsForValue().increment("icr:"+keyPrefix+statisticalUnit);
        return time_timestamp<<32 | count;
    }


    /**
     * 生成基准时间
     * @param args
     */
    public static void main(String[] args) {
       LocalDateTime time= LocalDateTime.of(2024,1,1,0,0,0);
       Long t= time.toEpochSecond(ZoneOffset.UTC);
        System.out.println(Long.toBinaryString(t).length());
    }
}
