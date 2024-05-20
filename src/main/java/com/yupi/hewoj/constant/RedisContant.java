package com.yupi.hewoj.constant;

/**
 * redis存储相关常量
 */
public interface RedisContant {
    String REDIS_QUESTION_CACHE_KEY="cache:question:";
    String REDIS_lOCK_QUESTION_KEY="lock:question:";
     Long CACHE_NULL_TTL = 2L;
    /**
     * vip秒杀卷库存
     */
     String SECKILL_STOCK_KEY = "seckill:stock:";

     String VOUCHER_ORDER="voucher:order:";


}
