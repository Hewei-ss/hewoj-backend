package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.entity.CommentReply;

import java.util.List;


/**
* @author 31695
* @description 针对表【comment_reply(对评论的回复表)】的数据库操作Service
* @createDate 2024-05-08 15:20:53
*/
public interface CommentReplyService extends IService<CommentReply> {
    List<CommentReply> replyCommentList(long targetCommentId);

}
