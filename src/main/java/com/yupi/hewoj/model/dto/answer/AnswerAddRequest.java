package com.yupi.hewoj.model.dto.answer;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerAddRequest implements Serializable {


    private long questionId;
    private long userId;
    private String title;
    private String answer;
    private static final long serialVersionUID = 1L;

}
