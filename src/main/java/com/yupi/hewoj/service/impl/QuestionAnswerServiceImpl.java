package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.QuestionAnswerMapper;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.dto.question.QuestionQueryRequest;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.service.QuestionAnswerService;
import com.yupi.hewoj.service.QuestionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 31695
* @description 针对表【question_answer(题目题解表)】的数据库操作Service实现
* @createDate 2024-04-25 09:53:18
*/
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer>
    implements QuestionAnswerService{


    @Override
    public QueryWrapper<QuestionAnswer> getQueryWrapper(AnswerQueryRequest answerQueryRequest) {
       QueryWrapper<QuestionAnswer> queryWrapper=new QueryWrapper<>();
       if(answerQueryRequest==null) return queryWrapper;
       long questionId= answerQueryRequest.getQuestionId();
       queryWrapper.eq(ObjectUtils.isNotEmpty(questionId),"questionId",questionId);
       return queryWrapper;
    }
}




