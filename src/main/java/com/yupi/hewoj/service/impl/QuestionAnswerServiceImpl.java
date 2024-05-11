package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.QuestionAnswerMapper;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionAnswerOne;
import com.yupi.hewoj.service.QuestionAnswerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 31695
 * @description 针对表【question_answer(题目题解表)】的数据库操作Service实现
 * @createDate 2024-04-25 09:53:18
 */
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswerOne>
        implements QuestionAnswerService {

    @Resource
    private QuestionAnswerMapper questionAnswerMapper;


    @Override
    public QueryWrapper<QuestionAnswerOne> getQueryWrapper(AnswerQueryRequest answerQueryRequest) {
        QueryWrapper<QuestionAnswerOne> queryWrapper = new QueryWrapper<>();
        if (answerQueryRequest == null) return queryWrapper;
        long questionId = answerQueryRequest.getQuestionId();
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        return queryWrapper;
    }

    @Override
    public QuestionAnswer getAnswerByAnswerId(long answerId) {
        return questionAnswerMapper.getAnswerByAnswerId(answerId);
    }
}




