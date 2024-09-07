package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.QuestionAnswerMapper;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.dto.user.UserQuerySelfAnswerRequest;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.User;
import com.yupi.hewoj.model.vo.QuestionVO;
import com.yupi.hewoj.model.vo.SelfAnswerVO;
import com.yupi.hewoj.service.AnswerThumbService;
import com.yupi.hewoj.service.QuestionAnswerService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 31695
 * @description 针对表【question_answer(题目题解表)】的数据库操作Service实现
 * @createDate 2024-04-25 09:53:18
 */
@Service
public class QuestionAnswerServiceImpl extends ServiceImpl<QuestionAnswerMapper, QuestionAnswer>
        implements QuestionAnswerService {

    @Resource
    private QuestionAnswerMapper questionAnswerMapper;

    @Resource
    private AnswerThumbService answerThumbService;


    @Override
    public QueryWrapper<QuestionAnswer> getQueryWrapper(AnswerQueryRequest answerQueryRequest) {
        QueryWrapper<QuestionAnswer> queryWrapper = new QueryWrapper<>();
        if (answerQueryRequest == null) return queryWrapper;
        long questionId = answerQueryRequest.getQuestionId();
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        return queryWrapper;
    }

    @Override
    public QueryWrapper<QuestionAnswer> getQueryWrapperForSelfAnswer(UserQuerySelfAnswerRequest userQuerySelfAnswerRequest,long userId) {
        QueryWrapper<QuestionAnswer> queryWrapper = new QueryWrapper<>();
        if (userQuerySelfAnswerRequest == null) return queryWrapper;
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        return queryWrapper;
    }

    /**
     * 返回题解信息并且返回登录用户是否点赞该题解
     * @param answerId
     * @param httpServletRequest
     * @return
     */
    @Override
    public QuestionAnswer getAnswerByAnswerId(long answerId, HttpServletRequest httpServletRequest) {
        QuestionAnswer questionAnswer = questionAnswerMapper.getAnswerByAnswerId(answerId);
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        Boolean isLike = answerThumbService.ifLiked(answerId, user.getId());
        questionAnswer.setIsLike(isLike);
        return questionAnswer;
    }

    @Override
    public Page<SelfAnswerVO> getSelfAnswersVOPage(Page<QuestionAnswer> questionAnswerPage) {
        List<QuestionAnswer> questionAnswerList = questionAnswerPage.getRecords();
        Page<SelfAnswerVO> selfAnswerVOPage = new Page<>(questionAnswerPage.getCurrent(), questionAnswerPage.getSize(), questionAnswerPage.getTotal());
        if (CollectionUtils.isEmpty(questionAnswerList)) {
            return selfAnswerVOPage;
        }
        List<SelfAnswerVO> selfAnswerVOList = questionAnswerList.stream().map(questionAnswer -> {
                    SelfAnswerVO selfAnswerVO = SelfAnswerVO.objToVo(questionAnswer);
                    return selfAnswerVO;
                }).collect(Collectors.toList());
            selfAnswerVOPage.setRecords(selfAnswerVOList);
        return selfAnswerVOPage;
    }
}




