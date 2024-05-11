package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionAnswerOne;

/**
* @author 31695
* @description 针对表【question_answer(题目题解表)】的数据库操作Service
* @createDate 2024-04-25 09:53:18
*/
public interface QuestionAnswerService extends IService<QuestionAnswerOne> {

    QueryWrapper<QuestionAnswerOne> getQueryWrapper(AnswerQueryRequest answerQueryRequest);

    QuestionAnswer getAnswerByAnswerId(long answerId);

}
