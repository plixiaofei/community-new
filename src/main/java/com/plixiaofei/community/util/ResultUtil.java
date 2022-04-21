package com.plixiaofei.community.util;

import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.domain.model.Result;

/**
 * Created on 2022/4/9 by plixiaofei
 */
public class ResultUtil {

    public static Result<Object> success(Object data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static Result<Object> fail() {
        return new Result<>(ResultCode.FAIL, null);
    }

}
