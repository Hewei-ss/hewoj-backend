package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.dto.user.UserQuerySelfAnswerRequest;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.vo.SelfAnswerVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 31695
* @description 针对表【question_answer(题目题解表)】的数据库操作Service
* @createDate 2024-04-25 09:53:18
*/
public interface QuestionAnswerService extends IService<QuestionAnswer> {

    QueryWrapper<QuestionAnswer> getQueryWrapper(AnswerQueryRequest answerQueryRequest);

    QueryWrapper<QuestionAnswer> getQueryWrapperForSelfAnswer(UserQuerySelfAnswerRequest userQuerySelfAnswerRequest,long userId);

    QuestionAnswer getAnswerByAnswerId(long answerId, HttpServletRequest httpServletRequest);

    Page<SelfAnswerVO> getSelfAnswersVOPage(Page<QuestionAnswer> questionAnswerPage);

}
