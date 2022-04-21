package com.plixiaofei.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plixiaofei.community.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author plixiaofei
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-04-08 15:06:05
* @Entity com.plixiaofei.common.model.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> listUsername();
}




