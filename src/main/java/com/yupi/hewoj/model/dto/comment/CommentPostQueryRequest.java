package com.yupi.hewoj.model.dto.comment;


import com.yupi.hewoj.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询帖子主评论的请求体
 */
@Data
public class CommentPostQueryRequest extends PageRequest implements Serializable {
    private Long postId;
    private static final long serialVersionUID = 1L;
}
