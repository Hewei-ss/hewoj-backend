package com.yupi.hewoj.service;

import javax.annotation.Resource;

import com.yupi.hewoj.mapper.CommentAnswerMapper;
import com.yupi.hewoj.model.entity.CommentAnswer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 用户服务测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;


    @Resource
    private CommentAnswerMapper commentAnswerMapper;


    @Test
    void listConmmentByPage(){
//        List<CommentAnswer> list=commentAnswerMapper.listConmmentByPage(1);
//        System.out.println(list);
    }

}
