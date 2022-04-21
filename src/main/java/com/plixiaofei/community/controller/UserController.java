package com.plixiaofei.community.controller;

import com.plixiaofei.community.domain.dto.UserDTO;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.domain.vo.UserVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2022/4/9 by plixiaofei
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public Result<Object> register(@RequestBody UserDTO userDTO) {
        String token = userService.register(userDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return new Result<>(ResultCode.SUCCESS, data);
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody UserDTO userDTO) {
        Map<String, Object> hashMap = userService.login(userDTO);
        return new Result<>(ResultCode.SUCCESS, hashMap);
    }

    @RequestMapping(value = "/api/changePassword", method = RequestMethod.POST)
    public Result<Object> changePassword(@RequestBody UserDTO userDTO) {
        userService.changePassword(userDTO);
        return new Result<>(ResultCode.SUCCESS, "修改密码成功");
    }

    @RequestMapping(value = "/api/getUserInfo", method = RequestMethod.GET)
    public Result<Object> getUserInfo(@RequestParam("username") String username) {
        return new Result<>(ResultCode.SUCCESS, userService.getUserInfo(username));
    }


    @RequestMapping(value = "/api/changeUserInfo", method = RequestMethod.POST)
    public Result<Object> changeUserInfo(@RequestBody UserVO userVO) {
        UserVO data = userService.updateUserInfo(userVO);
        return new Result<>(ResultCode.SUCCESS, data);
    }

    @RequestMapping(value = "/api/changeUserIcon", method = RequestMethod.POST)
    public Result<Object> changeIcon(@RequestParam("multipartIcon") MultipartFile icon,
                                     @RequestParam("username") String username) {
        userService.changeIcon(username, icon);
        return new Result<>(ResultCode.SUCCESS, "修改个人头像成功");
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    public Result<Object> logout(HttpServletRequest request) {
        return new Result<>(ResultCode.SUCCESS, "账号退出成功");
    }
}
