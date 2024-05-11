package com.yupi.hewoj.model.dto.comment;


import com.yupi.hewoj.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询主评论的请求体
 */
@Data
public class CommentQueryRequest extends PageRequest implements Serializable {
    private Long answerId;
    /**
     * 被回复对象的id
     */
    private static final long serialVersionUID = 1L;
}
