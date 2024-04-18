package com.yupi.hewoj.common;

import java.io.Serializable;

import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public BaseResponse(ResponseCodeEnum ResponseCodeEnum) {
        this(ResponseCodeEnum.getCode(), null, ResponseCodeEnum.getMessage());
    }
}
