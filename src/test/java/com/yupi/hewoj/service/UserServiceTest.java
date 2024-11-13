package com.yupi.hewoj.service;

import javax.annotation.Resource;

import cn.hutool.json.JSONUtil;
import com.yupi.hewoj.mapper.CommentAnswerMapper;
import com.yupi.hewoj.model.entity.CommentAnswer;
import com.yupi.hewoj.utils.RedisIdWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER;

/**
 * 用户服务测试
 *
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;


    @Resource
    private CommentAnswerMapper commentAnswerMapper;
    @Resource
    RedisIdWorker redisIdWorker;

  //  @Test

//    void test(){
//        String statisticalUnit = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
//        long orderId = redisIdWorker.nextId(VOUCHER_ORDER + 5, statisticalUnit);
//        System.out.println(orderId);
//    }

    @Test
    void listConmmentByPage(){
//        List<CommentAnswer> list=commentAnswerMapper.listConmmentByPage(1);
//        System.out.println(list);
    }

}
