package com.yupi.hewoj.model.dto.user;

import com.yupi.hewoj.common.PageRequest;
import lombok.Data;

import java.io.Serializable;


@Data
public class UserQuerySelfAnswerRequest extends PageRequest implements Serializable {

    private Long userId;
    private static final long serialVersionUID = 1L;

}
