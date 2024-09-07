package com.yupi.hewoj.controller;


import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.exception.ThrowUtils;
import com.yupi.hewoj.model.dto.comment.*;
import com.yupi.hewoj.model.entity.*;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.service.CommentAnswerService;
import com.yupi.hewoj.service.CommentPostReplyService;
import com.yupi.hewoj.service.CommentPostService;
import com.yupi.hewoj.service.CommentReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentAnswerService commentAnswerService;

    @Resource
    private CommentReplyService commentReplyService;

    @Resource
    private CommentPostService commentPostService;
    @Resource
    private CommentPostReplyService commentPostReplyService;

    /**
     * 提交主评论
     *
     * @param commentAddRequest
     * @return
     */
    @PostMapping("/answer/addComment")
    public BaseResponse<Long> addComment(@RequestBody CommentAddRequest commentAddRequest, HttpServletRequest httpServletRequest) {
        if (commentAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        CommentAnswer comment = new CommentAnswer();
        BeanUtils.copyProperties(commentAddRequest, comment);
        commentAnswerService.save(comment);
        return ResultUtils.success(comment.getId());
    }

    /**
     * 提交对帖子的主评论
     * @param commentPostAddRequest
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/post/addComment")
    public BaseResponse<Long> addCommentPost(@RequestBody CommentPostAddRequest commentPostAddRequest, HttpServletRequest httpServletRequest) {
        if (commentPostAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        CommentPost commentPost = new CommentPost();
        BeanUtils.copyProperties(commentPostAddRequest, commentPost);
        commentPostService.save(commentPost);
        return ResultUtils.success(commentPost.getId());
    }

    /**
     * 提交副评论(对评论的评论)
     *
     * @param commentReplyAddRequest
     * @return
     */
    @PostMapping("/answer/addReplyComment")
    public BaseResponse<Long> addReplyComment(@RequestBody CommentReplyAddRequest commentReplyAddRequest, HttpServletRequest httpServletRequest) {
        if (commentReplyAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        CommentReply commentReply = new CommentReply();
        BeanUtils.copyProperties(commentReplyAddRequest, commentReply);
        commentReply.setUserId(user.getId());
        commentReplyService.save(commentReply);
        return ResultUtils.success(commentReply.getId());
    }

    /**
     * 提交对帖子的副评论(对评论的评论)
     * @param commentPostReplyAddRequest
     * @param httpServletRequest
     * @return
     */


    @PostMapping("/post/addReplyComment")
    public BaseResponse<Long> addReplyCommentPost(@RequestBody CommentPostReplyAddRequest commentPostReplyAddRequest, HttpServletRequest httpServletRequest) {
        if (commentPostReplyAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) throw new BusinessException(ResponseCodeEnum.NOT_LOGIN_ERROR);
        CommentPostReply commentPostReply = new CommentPostReply();
        BeanUtils.copyProperties(commentPostReplyAddRequest, commentPostReply);
        commentPostReply.setUserId(user.getId());
        commentPostReplyService.save(commentPostReply);
        return ResultUtils.success(commentPostReply.getId());
    }


    /**
     * 根据题解id获取主评论分页信息
     *
     * @param commentQueryRequest
     * @return
     */
    @PostMapping("/answer/list/page/comment")
    public BaseResponse<PageInfo<CommentAnswer>> listConmmentByPage(@RequestBody CommentQueryRequest commentQueryRequest) {
        if (commentQueryRequest.getAnswerId() < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }

        int current = commentQueryRequest.getCurrent();
        int size = commentQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        PageInfo<CommentAnswer> page = commentAnswerService.listConmmentByPage(current, size, commentQueryRequest.getAnswerId());
        return ResultUtils.success(page);
    }

    /**
     * 根据帖子id获取主评论分页信息
     *
     * @param commentPostQueryRequest
     * @return
     */
    @PostMapping("/post/list/page/comment")
    public BaseResponse<PageInfo<CommentPost>> listConmmentPostByPage(@RequestBody CommentPostQueryRequest commentPostQueryRequest) {
        if (commentPostQueryRequest.getPostId() < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }

        int current = commentPostQueryRequest.getCurrent();
        int size = commentPostQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
        PageInfo<CommentPost> page = commentPostService.listConmmentPostByPage(current, size, commentPostQueryRequest.getPostId());
        return ResultUtils.success(page);
    }


    /**
     * 获取副评论
     *
     * @param replyCommentId
     * @return
     */
    @GetMapping("/answer/list/reply/comment")
    public BaseResponse<List<CommentReply>> listReplyConmment(long replyCommentId) {
        if (replyCommentId < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        List<CommentReply> list = commentReplyService.replyCommentList(replyCommentId);
        return ResultUtils.success(list);
    }

    @GetMapping("/post/list/reply/comment")
    public BaseResponse<List<CommentPostReply>> listPostReplyCommment(long postReplyCommentId) {
        if (postReplyCommentId < 0) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        List<CommentPostReply> list = commentPostReplyService.postReplyCommentList(postReplyCommentId);
        return ResultUtils.success(list);
    }


}
