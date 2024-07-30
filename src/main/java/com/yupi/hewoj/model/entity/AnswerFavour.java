package com.yupi.hewoj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题解收藏
 *
 * @TableName answer_favour
 */
@TableName(value = "answer_favour")
@Data
public class AnswerFavour implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题解 id
     */
    private Long answerId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private QuestionAnswer questionAnswer;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}