package com.yupi.hewoj.exception;

import com.yupi.hewoj.model.enums.ResponseCodeEnum;

/**
 * 自定义异常类
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;
    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
    }

    public BusinessException(ResponseCodeEnum responseCode, String message) {
        super(message);
        this.code = responseCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
