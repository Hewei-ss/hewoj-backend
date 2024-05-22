package com.yupi.hewoj;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER_LOCK;

/**
 * 主类测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
@Slf4j
class MainApplicationTests {

    @Resource
    private RedissonClient redissonClient;

    private RLock lock;

    @Test
    public void test() {
        lock = redissonClient.getLock(VOUCHER_ORDER_LOCK + 1 + ":" + 2);
        boolean isLock = lock.tryLock();
        if (!isLock) {
            log.info("获取锁失败---1");
            return;
        }
        try {
            log.info("获取锁成功---1");
            method2();
            log.info("开始执行业务---1");
        } finally {
            log.info("准备释放锁---1");
            lock.unlock();
        }

    }

    void method2() {
        boolean isLock = lock.tryLock();
        if (!isLock) {
            log.info("获取锁失败---2");
            return;
        }
        try {
            log.info("获取锁成功---2");
            log.info("开始执行业务---2");
        } finally {
            log.info("准备释放锁---2");
            lock.unlock();
        }
    }
}
