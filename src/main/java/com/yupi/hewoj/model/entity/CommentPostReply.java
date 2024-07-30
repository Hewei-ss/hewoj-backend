package com.yupi.hewoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 对贴子评论的回复表
 *
 * @TableName comment_post_reply
 */
@TableName(value = "comment_post_reply")
@Data
public class CommentPostReply implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}