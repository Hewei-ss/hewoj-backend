package com.yupi.hewoj.exception;

import com.yupi.hewoj.model.enums.ResponseCodeEnum;

/**
 * 抛异常工具类
 *
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛自定义异常
     *
     * @param condition
     * @param responseCode
     */
    public static void throwIf(boolean condition, ResponseCodeEnum responseCode) {
        throwIf(condition, new BusinessException(responseCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param responseCode
     * @param message
     */
    public static void throwIf(boolean condition, ResponseCodeEnum responseCode, String message) {
        throwIf(condition, new BusinessException(responseCode, message));
    }
}
