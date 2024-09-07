package com.yupi.hewoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.exception.ThrowUtils;
import com.yupi.hewoj.model.dto.answer.AnswerAddLikeRequest;
import com.yupi.hewoj.model.dto.answer.AnswerAddRequest;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.QuestionAnswer;
import com.yupi.hewoj.model.entity.User;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.service.AnswerThumbService;
import com.yupi.hewoj.service.QuestionAnswerService;
import com.yupi.hewoj.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;


@RestController
@RequestMapping("/answer")
public class QuestionAnswerController {

    @Resource
    private QuestionAnswerService questionAnswerService;

    @Resource
    private AnswerThumbService answerThumbService;

    /**
     * 添加题解
     *
     * @param answerAddRequest
     * @return
     */
    @PostMapping("/addAswer")
    public BaseResponse<Long> addAnswer(@RequestBody AnswerAddRequest answerAddRequest) {
        if (answerAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        QuestionAnswer answer = new QuestionAnswer();
        BeanUtils.copyProperties(answerAddRequest, answer);
        questionAnswerService.save(answer);
        return ResultUtils.success(answer.getId());
    }

    /**
     * 分页获取题解
     *
     * @param answerQueryRequest
     * @return
     */
    @PostMapping("/list/page/")
    public BaseResponse<Page<QuestionAnswer>> getAnswerByQuestionId(@RequestBody AnswerQueryRequest answerQueryRequest) {
        if (answerQueryRequest.getQuestionId() < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }

        // long current = answerQueryRequest.getCurrent();
        long size = answerQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        Page<QuestionAnswer> questionAnswerPage = questionAnswerService.page(new Page<>(), questionAnswerService.getQueryWrapper(answerQueryRequest));
        return ResultUtils.success(questionAnswerPage);
    }

    /**
     * 更具题解id获取题解并且查询出登录用户是否给该题解点过赞
     *
     * @param answerId
     * @return
     */

    @GetMapping("/get")
    public BaseResponse<QuestionAnswer> getAnswerById(Long answerId, HttpServletRequest httpServletRequest) {
        if (answerId <= 0) throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        QuestionAnswer questionAnswer = questionAnswerService.getAnswerByAnswerId(answerId, httpServletRequest);
        if (questionAnswer == null) throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR);
        return ResultUtils.success(questionAnswer);
    }

    @PostMapping("/addLike")
    public BaseResponse<Boolean> addAnswerLike(@RequestParam("answerId") Long answerId, HttpServletRequest httpServletRequest) {
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        Boolean ch = answerThumbService.likeAnswerOrNot(answerId, user.getId());
        return ResultUtils.success(ch);
    }

}
