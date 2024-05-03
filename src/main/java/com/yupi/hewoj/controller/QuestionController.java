package com.yupi.hewoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.yupi.hewoj.annotation.AuthCheck;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.DeleteRequest;
import com.yupi.hewoj.common.PageResponse;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.constant.UserConstant;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.exception.ThrowUtils;
import com.yupi.hewoj.model.dto.answer.AnswerAddRequest;
import com.yupi.hewoj.model.dto.answer.AnswerQueryRequest;
import com.yupi.hewoj.model.dto.comment.CommentAddRequest;
import com.yupi.hewoj.model.dto.comment.CommentQueryRequest;
import com.yupi.hewoj.model.dto.question.*;
import com.yupi.hewoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.hewoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.yupi.hewoj.model.entity.*;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.model.vo.QuestionSubmitVO;
import com.yupi.hewoj.model.vo.QuestionVO;
import com.yupi.hewoj.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 题目接口
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;
    @Resource
    private QuestionSubmitService questionsubmitbService;


    @Resource
    private QuestionAnswerService questionAnswerService;

    @Resource
    private CommentAnswerService commentAnswerService;

    private final static Gson GSON = new Gson();


    /**
     * 创建
     *
     * @param questionAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest, HttpServletRequest request) {
        if (questionAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Question question = new Question();

        BeanUtils.copyProperties(questionAddRequest, question);
        List<String> tags = questionAddRequest.getTags();
        if (tags != null) {
            question.setTags(GSON.toJson(tags));
        }
        List<JudgeCase> judgeCase = questionAddRequest.getJudgeCase();
        if (judgeCase != null) {
            question.setJudgeCase(GSON.toJson(judgeCase));
        }
        JudgeConfig judgeConfig = questionAddRequest.getJudgeConfig();
        if (judgeConfig != null) {
            question.setJudgeConfig(GSON.toJson(judgeConfig));
        }
        questionService.validQuestion(question, true);
        User loginUser = userService.getLoginUser(request);
        question.setUserId(loginUser.getId());
        question.setFavourNum(0);
        question.setThumbNum(0);
        boolean result = questionService.save(question);
        ThrowUtils.throwIf(!result, ResponseCodeEnum.OPERATION_ERROR);
        long newQuestionId = question.getId();
        return ResultUtils.success(newQuestionId);
    }


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
     * 提交评论
     *
     * @param commentAddRequest
     * @return
     */
    @PostMapping("/addComment")
    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest) {
        if (commentAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        CommentAnswer comment = new CommentAnswer();
        BeanUtils.copyProperties(commentAddRequest, comment);
        commentAnswerService.save(comment);
        return ResultUtils.success(comment.getId());
    }


    /**
     * 分页获取题解
     * @param answerQueryRequest
     * @return
     */
    @PostMapping("/answer/list/page/")
    public BaseResponse<Page<QuestionAnswer>> getAnswerByQuestionId(@RequestBody AnswerQueryRequest answerQueryRequest) {
        if (answerQueryRequest.getQuestionId() < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }

        long current = answerQueryRequest.getCurrent();
        long size = answerQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        Page<QuestionAnswer> questionAnswerPage = questionAnswerService.page(new Page<>(), questionAnswerService.getQueryWrapper(answerQueryRequest));
        return ResultUtils.success(questionAnswerPage);
    }


    /**
     * 根据题解id获取主评论分页信息
     * @param commentQueryRequest
     * @return
     */
    @GetMapping("/list/page/comment")
    public BaseResponse<PageResponse> listConmmentByPage(@RequestBody CommentQueryRequest commentQueryRequest){
        if (commentQueryRequest.getAnswerId() < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }

        long current = commentQueryRequest.getCurrent();
        long size = commentQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        PageResponse page=commentAnswerService.listConmmentByPage(current,size,commentQueryRequest.getAnswerId());
    }

    /**
     * 分页获取列表（封装类）
     *
     * @param questionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionVO>> listQuestionVOByPage(@RequestBody QuestionQueryRequest questionQueryRequest, HttpServletRequest request) {

        //当前要返回的页码
        long current = questionQueryRequest.getCurrent();
        //页面大小
        long size = questionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        Page<Question> questionPage = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionQueryRequest));
        return ResultUtils.success(questionService.getQuestionVOPage(questionPage, request));
    }

    /**
     * 根据 id 获取不脱敏的数据
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Question> getQuestionById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Question question = questionService.getById(id);
        if (question == null) {
            throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        // 不是本人或管理员，不能直接获取所有信息
        if (!question.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
        }
        return ResultUtils.success(question);
    }


    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Question oldQuestion = questionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ResponseCodeEnum.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldQuestion.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
        }
        boolean b = questionService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param questionUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestion(@RequestBody QuestionUpdateRequest questionUpdateRequest) {
        if (questionUpdateRequest == null || questionUpdateRequest.getId() <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionUpdateRequest, question);
        List<String> tags = questionUpdateRequest.getTags();
        if (tags != null) {
            question.setTags(GSON.toJson(tags));
        }
        List<JudgeCase> judgeCase = questionUpdateRequest.getJudgeCase();
        if (judgeCase != null) {
            question.setJudgeCase(GSON.toJson(judgeCase));
        }
        JudgeConfig judgeConfig = questionUpdateRequest.getJudgeConfig();
        if (judgeConfig != null) {
            question.setJudgeConfig(GSON.toJson(judgeConfig));
        }
        // 参数校验
        questionService.validQuestion(question, false);
        long id = questionUpdateRequest.getId();
        // 判断是否存在
        Question oldQuestion = questionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ResponseCodeEnum.NOT_FOUND_ERROR);
        boolean result = questionService.updateById(question);
        return ResultUtils.success(result);
    }


    /**
     * 根据 id 获取脱敏的数据
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionVO> getQuestionVOById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Question question = questionService.getById(id);
        if (question == null) {
            throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(questionService.getQuestionVO(question, request));
    }


    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param questionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public BaseResponse<Page<QuestionVO>> listMyQuestionVOByPage(@RequestBody QuestionQueryRequest questionQueryRequest,
                                                                 HttpServletRequest request) {
        if (questionQueryRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        questionQueryRequest.setUserId(loginUser.getId());
        long current = questionQueryRequest.getCurrent();
        long size = questionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        Page<Question> questionPage = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionQueryRequest));
        return ResultUtils.success(questionService.getQuestionVOPage(questionPage, request));
    }


    /**
     * 编辑（用户）
     *
     * @param questionEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editQuestion(@RequestBody QuestionEditRequest questionEditRequest, HttpServletRequest request) {
        if (questionEditRequest == null || questionEditRequest.getId() <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionEditRequest, question);
        List<String> tags = questionEditRequest.getTags();
        if (tags != null) {
            question.setTags(GSON.toJson(tags));
        }
        // 参数校验
        questionService.validQuestion(question, false);
        User loginUser = userService.getLoginUser(request);
        long id = questionEditRequest.getId();
        // 判断是否存在
        Question oldQuestion = questionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ResponseCodeEnum.NOT_FOUND_ERROR);
        // 仅本人或管理员可编辑
        if (!oldQuestion.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR);
        }
        boolean result = questionService.updateById(question);
        return ResultUtils.success(result);
    }


    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/question_submit/do")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionsubmitbService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);

    }


    /**
     * 分页获取题目提交列表（除了管理员外，普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/question_submit/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionsubmitbService.page(new Page<>(current, size),
                questionsubmitbService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionsubmitbService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }

}
