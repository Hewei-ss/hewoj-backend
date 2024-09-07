package com.yupi.hewoj.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yupi.hewoj.model.vo.QuestionVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<Long, QuestionVO> itemCache(){
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(10_000)
                .build();
    }

//    @Bean
//    public Cache<Long, ItemStock> stockCache(){
//        return Caffeine.newBuilder()
//                .initialCapacity(100)
//                .maximumSize(10_000)
//                .build();
//    }
}