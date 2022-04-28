package com.plixiaofei.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plixiaofei.community.domain.dto.UserDTO;
import com.plixiaofei.community.domain.model.User;
import com.plixiaofei.community.domain.vo.UserVO;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.mapper.UserMapper;
import com.plixiaofei.community.service.UserService;
import com.plixiaofei.community.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author plixiaofei
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-04-08 15:06:05
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Value("${storage.user.avatar}")
    private String avatarStore;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String register(UserDTO userDTO) {
        List<String> usernames = userMapper.listUsername().stream().map(User::getUsername).collect(Collectors.toList());
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        Date nowTime = new Date(System.currentTimeMillis());
        user.setCreateTime(nowTime);
        user.setLoginTime(nowTime);
        if (usernames.contains(user.getUsername())) {
            throw new CustomException(ResultCode.USERNAME_EXISTS);
        } else {
            if (userMapper.insert(user) == 0) {
                log.warn("{} 注册失败", user.getUsername());
                throw new CustomException(ResultCode.CREATE_USER_FAIL);
            } else {
                return JwtUtil.getToken(user.getUsername());
            }
        }
    }

    @Transactional
    @Override
    public Map<String, Object> login(UserDTO userDTO) {
        Map<String, Object> hashMap = new HashMap<>();
        List<String> usernames = userMapper.listUsername().stream().map(User::getUsername).collect(Collectors.toList());

        if (!usernames.contains(userDTO.getUsername())) {
            throw new CustomException(ResultCode.USER_NOT_EXISTS);
        } else {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userDTO.getUsername());
            queryWrapper.eq("password", userDTO.getPassword());
            List<User> users = userMapper.selectList(queryWrapper);
            if (users.isEmpty()) {
                log.warn("{} 登陆失败", userDTO.getUsername());
                throw new CustomException(ResultCode.PASSWORD_WRONG);
            } else {
                User user = users.get(0);
                UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
                updateWrapper
                        .eq("id", user.getId())
                        .set("login_time", Calendar.getInstance().getTime());
                userMapper.update(user, updateWrapper);
                String token = JwtUtil.getToken(userDTO.getUsername());
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                hashMap.put("userInfo", userVO);
                hashMap.put("token", token);
                return hashMap;
            }
        }
    }

    @Transactional
    @Override
    public void changePassword(UserDTO userDTO) {
        List<String> usernames = userMapper.listUsername().stream().map(User::getUsername).collect(Collectors.toList());
        if (!usernames.contains(userDTO.getUsername())) {
            throw new CustomException(ResultCode.USER_NOT_EXISTS);
        } else {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userDTO.getUsername());
            List<User> users = userMapper.selectList(queryWrapper);
            if (users.isEmpty()) {
                throw new CustomException(ResultCode.USER_NOT_EXISTS);
            } else {
                User user = users.get(0);
                user.setPassword(userDTO.getPassword());
                int updateStatus = userMapper.updateById(user);
                if (updateStatus == 0) {
                    log.warn("{} 修改密码失败", userDTO.getUsername());
                    throw new CustomException(ResultCode.CHANGE_PASSWORD_FAIL);
                }
            }
        }
    }

    @Transactional
    @Override
    public UserVO updateUserInfo(UserVO userVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userVO.getUsername());
        User originUser = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(originUser)) {
            throw new CustomException(ResultCode.USER_NOT_EXISTS);
        } else {
            userVO.setEmail(originUser.getEmail());
            userVO.setIcon(originUser.getIcon());
            originUser.setUsername(userVO.getUsername());
            int updateStatus = userMapper.updateById(originUser);
            if (updateStatus == 0) {
                log.warn("{} 修改资料失败", userVO.getUsername());
                throw new CustomException(ResultCode.CHANGE_PERSONAL_INFO_FAIL);
            } else {
                return userVO;
            }
        }
    }

    @Transactional
    @Override
    public void changeIcon(String username, MultipartFile icon) {
//        Path storePath = Paths.get(avatarStore, icon.getOriginalFilename());
        // 应该使用 UUID 或其他存储，不然大概率重复
        String filename = icon.getOriginalFilename();
        String ext = null;
        if (filename != null) {
            ext = filename.substring(filename.lastIndexOf('.') + 1);
        }
        String randomAvatarName = username + "." + UUID.randomUUID() + "." + ext;
        Path storePath = Paths.get(avatarStore, randomAvatarName);
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                throw new CustomException(ResultCode.USER_NOT_EXISTS);
            }
            user.setIcon(storePath.toString());
            userMapper.updateById(user);
            icon.transferTo(storePath);
        } catch (IOException e) {
            log.warn("保存图标失败");
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public UserVO getUserInfo(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User originUser = userMapper.selectOne(queryWrapper);
        UserVO res = new UserVO();
        BeanUtils.copyProperties(originUser, res);
        return res;
    }
}



