package com.yupi.hewoj.common;

import com.yupi.hewoj.model.enums.ResponseCodeEnum;

/**
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(ResponseCodeEnum.SUCCESS.getCode(), data, ResponseCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 失败
     *
     * @param responseCodeEnum
     * @return
     */
    public static BaseResponse error(ResponseCodeEnum responseCodeEnum) {
        return new BaseResponse<>(responseCodeEnum);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @return
     */
    public static BaseResponse error(int code, String message) {

        return new BaseResponse(code, null, message);
    }

    /**
     * 失败
     *
     * @param responseCodeEnum
     * @return
     */
    public static BaseResponse error(ResponseCodeEnum responseCodeEnum, String message) {
        return new BaseResponse(responseCodeEnum.getCode(), null, message);
    }
}
