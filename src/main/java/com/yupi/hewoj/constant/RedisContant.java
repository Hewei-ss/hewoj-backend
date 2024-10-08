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
     String VOUCHER_ORDER_LOCK="voucher:order:lock:";
     String ANSWER_LIKE_USERS_KEY="answer:like:users:";
     String ANSWER_LIKE_COUNT="answer:like:count:";


}
