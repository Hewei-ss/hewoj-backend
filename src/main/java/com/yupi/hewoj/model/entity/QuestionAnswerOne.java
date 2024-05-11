package com.yupi.hewoj.model.entity;

/**
 * 单表查询
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目题解表
 *
 * @TableName question_answer
 */
@TableName(value = "question_answer")
@Data
public class QuestionAnswerOne implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 题解内容
     */
    private String answer;

    /**
     * 查看人数
     */
    private Integer lookCnt;

    /**
     * 点赞人数
     */
    private Integer likeCnt;

    /**
     * 评论数
     */
    private Integer commentCnt;
    /**
     * 题解标题
     */

    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 逻辑删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}