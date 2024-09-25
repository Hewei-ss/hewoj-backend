package com.yupi.hewoj.utils.distributedlock.impl;

import cn.hutool.core.lang.UUID;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.utils.distributedlock.DistributedLock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class DistributedLockimpl implements DistributedLock {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";
    /**
     * 将unlock的lua申明无畏静态的，这样避免每次调用的时候都去lua脚本文件中读取lua脚本，避免频繁io浪费时间
     */
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    //在静态代码框中初始化UNLOCK_SCRIPT，防止每次调用lua脚本都要进行oi读取lua脚本
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    @Override
    public Boolean tryLock(String keyPrefix,long voucherId,long userId,long timeout, TimeUnit unit) {
        long currentThreadId = Thread.currentThread().getId();
        String value=ID_PREFIX+currentThreadId;
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(keyPrefix+voucherId+":" + userId, value, timeout, unit);
        if(success==false){
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR,"不能重复下单");
        }
        //spring 自动拆箱可能会发生空指针异常
        return Boolean.TRUE.equals(success);
    }

    /**
     * 使用lua脚本文件保证查询和删除redis中的key的两个操作的原子性
     * @param keyPrefix
     * @param voucherId
     * @param userId
     */
    @Override
    public void unLock(String keyPrefix, long voucherId, long userId) {
        String key=keyPrefix+voucherId+":"+userId;
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(key),
                ID_PREFIX + Thread.currentThread().getId());
    }
}
