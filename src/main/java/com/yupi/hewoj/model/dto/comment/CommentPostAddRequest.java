package com.yupi.hewoj.model.dto.comment;


import lombok.Data;

import java.io.Serializable;

/**
 * 提交评论请求封装类
 */

@Data
public class CommentPostAddRequest implements Serializable {

    private long postId;
    private long userId;
    /**
     * 评论内容
     */
    private String commentContent;

    private static final long serialVersionUID = 1L;
}
