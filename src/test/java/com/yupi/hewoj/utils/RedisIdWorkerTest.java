package com.yupi.hewoj.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisIdWorkerTest {
    @Resource
    RedisIdWorker redisIdWorker;

    private ExecutorService es = Executors.newFixedThreadPool(500);

    @Test
    void test(){
        long time_timestamp=24713483;
        System.out.println( time_timestamp<<32 | 2);
    }


//    @Test
//    public void TestnextId() throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(30);
//        Runnable task=()->{
//            for(int i=0;i<100;i++){
//                long id=redisIdWorker.nextId("question","202401");
//                System.out.println(id);
//            }
//            latch.countDown();
//        };
//
//        long begin = System.currentTimeMillis();
//        for(int i=0;i<300;i++){
//            es.submit(task);
//        }
//        latch.await();
//        long end = System.currentTimeMillis();
//        System.out.println("time = " + (end - begin));
//
//
//    }

}