package com.yupi.hewoj.exception;

import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
//@RestControllerAdvice：这是一个 Spring 注解，用于声明 GlobalExceptionHandler 类是一个全局异常处理器，它可以捕获在控制器（Controller）中抛出的异常。
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ResponseCodeEnum.SYSTEM_ERROR, "系统错误");
    }
}
