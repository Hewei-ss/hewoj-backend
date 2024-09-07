package com.yupi.hewoj.model.dto.answer;

import com.yupi.hewoj.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerQueryRequest extends PageRequest implements Serializable {
    private Long questionId;
    private static final long serialVersionUID = 1L;
}
