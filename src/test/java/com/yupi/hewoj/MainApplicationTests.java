package com.yupi.hewoj;

import javax.annotation.Resource;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER_LOCK;

/**
 * 主类测试
 *
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

    @Test
    void test1(){
        // 1.创建符合条件的布隆过滤器
        // 预期数据量10000，错误率0.0001
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(
                        Charset.forName("utf-8")),10000, 0.0001);
        // 2.将一部分数据添加进去
        for (int i = 0; i < 5000; i++) {
            bloomFilter.put("" + i);
        }
        System.out.println("数据写入完毕");
        // 3.测试结果
        for (int i = 0; i < 10000; i++) {
            if (bloomFilter.mightContain("" + i)) {
                System.out.println(i + "存在");
            } else {
                System.out.println(i + "不存在");
            }
        }
    }
}
