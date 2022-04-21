package com.plixiaofei.community.controller;

import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.enumeration.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2022/4/9 by plixiaofei
 */
@RestController
public class IndexController {
    @RequestMapping(value = "/api/index", method = RequestMethod.GET)
    public Result<Object> index() {
        Map<String, Object> hashMap = new HashMap<>();
        return new Result<>(ResultCode.SUCCESS, hashMap);
    }

}
