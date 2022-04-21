package com.plixiaofei.community.controller;

import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.domain.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2022/4/9 by plixiaofei
 */
@RestController
public class TestController {

    @RequestMapping(value = "/api/success", method = RequestMethod.GET)
    public Result<Object> success() {
        return new Result<>(ResultCode.SUCCESS, "成功了");
    }

    @RequestMapping(value = "/api/fail", method = RequestMethod.GET)
    public Result<Object> fail() {
        return new Result<>(ResultCode.FAIL, "失败了");
    }

    @RequestMapping(value = "/api/error", method = RequestMethod.GET)
    public Result<Object> error() {
        throw new CustomException(ResultCode.INTERNAL_ERROR);
    }

    @RequestMapping(value = "/api/esTest", method = RequestMethod.GET)
    public Result<Object> esTest() {
        return new Result<>(ResultCode.SUCCESS, null);
    }
}
