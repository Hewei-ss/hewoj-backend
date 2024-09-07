package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.entity.CommentPostReply;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_post_reply(对贴子评论的回复表)】的数据库操作Service
* @createDate 2024-06-07 05:02:41
*/
public interface CommentPostReplyService extends IService<CommentPostReply> {

    List<CommentPostReply> postReplyCommentList(long postreplyCommentId);
}
