package com.yupi.hewoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题解评论表
 * @TableName comment_answer
 */
@TableName(value ="comment_answer")
@Data
public class CommentAnswer implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题解id
     */
    private Long answerId;

    /**
     * 评论内容
     */
    private String commentContent;

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
    private User user;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}