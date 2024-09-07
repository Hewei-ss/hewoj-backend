package com.yupi.hewoj.model.dto.comment;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
/**
 * 添加副评论的请求
 */
public class CommentReplyAddRequest implements Serializable {

    /**
     * 回复人的id
     */
    private Long userId;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 被回复的评论Id
     */
    private Long targetCommentId;

    /**
     * 被回复人的id
     */
    private Long targetUserId;
    private static final long serialVersionUID = 1L;



}
