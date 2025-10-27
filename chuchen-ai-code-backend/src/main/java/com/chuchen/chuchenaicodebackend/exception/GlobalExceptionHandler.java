package com.chuchen.chuchenaicodebackend.exception;

import com.chuchen.chuchenaicodebackend.common.BaseResponse;
import com.chuchen.chuchenaicodebackend.common.ResultUtils;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chuchen
 * @date 2025/5/29 17:00
 * @description 全局异常处理器
 */
@Hidden
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: {}", e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> businessExceptionHandler(RuntimeException e) {
        log.error("RuntimeException: {}", e.getMessage());
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统异常，请联系管理员");
    }
}
