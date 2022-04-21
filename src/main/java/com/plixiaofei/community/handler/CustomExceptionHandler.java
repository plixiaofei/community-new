package com.plixiaofei.community.handler;

import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.enumeration.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2022/4/9 by plixiaofei
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> handler(Exception ex) {
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            return new Result<>(customException.getCode(), customException.getMessage(), null);
        } else {
            log.error(ex.getMessage());
            return new Result<>(ResultCode.INTERNAL_ERROR, null);
        }
    }
}
