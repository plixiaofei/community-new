package com.plixiaofei.community.exception;

import com.plixiaofei.community.enumeration.ResultCode;

/**
 * Created on 2022/4/9 by plixiaofei
 */
public class CustomException extends RuntimeException {
    private Integer code;

    // 构造 异常类
    public CustomException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
