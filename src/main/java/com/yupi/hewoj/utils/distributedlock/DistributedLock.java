package com.yupi.hewoj.utils.distributedlock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 */
public interface DistributedLock {

    /**
     * 获取分布式锁
     * @return
     */
    public Boolean tryLock(String keyPrefix, long voucherId,long userId, long timeout, TimeUnit unit);

    /**
     * 释放分布式锁
     * @return
     */
    public void unLock(String keyPrefix, long voucherId,long userId);
}
