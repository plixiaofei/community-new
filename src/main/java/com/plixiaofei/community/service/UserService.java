package com.plixiaofei.community.service;

import com.plixiaofei.community.domain.dto.UserDTO;
import com.plixiaofei.community.domain.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plixiaofei.community.domain.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
* @author plixiaofei
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-04-08 15:06:05
*/
public interface UserService extends IService<User> {
    String register(UserDTO userDTO);

    Map<String, Object> login(UserDTO userDTO);

    void changePassword(UserDTO userDTO);

    /**
     * 修改个人信息
     */
    UserVO updateUserInfo(UserVO userVO);

    void changeIcon(String username, MultipartFile icon);

    UserVO getUserInfo(String username);
}
