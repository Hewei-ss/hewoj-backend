package com.yupi.hewoj.model.dto.post;

import com.yupi.hewoj.model.dto.question.JudgeCase;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PostAddRequest implements Serializable {

    /**
     * 图片（json 数组）
     */
    private List<String> images;

    /**
     * 内容
     */
    private String content;


    /**
     * 创建用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}