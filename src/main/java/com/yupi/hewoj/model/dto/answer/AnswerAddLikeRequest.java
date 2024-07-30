package com.yupi.hewoj.model.dto.answer;


import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerAddLikeRequest implements Serializable {
    private Long userId;
    private Long answerId;
    private static final long serialVersionUID = 1L;
}
