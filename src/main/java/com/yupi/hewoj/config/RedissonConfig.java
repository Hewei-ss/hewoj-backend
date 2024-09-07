package com.yupi.hewoj.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        // 配置
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setPassword("hewei135790").setDatabase(2);
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
