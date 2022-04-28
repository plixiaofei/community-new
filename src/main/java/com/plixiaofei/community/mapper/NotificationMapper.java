package com.plixiaofei.community.mapper;

import com.plixiaofei.community.domain.model.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author plixiaofei
* @description 针对表【notification(通知表)】的数据库操作Mapper
* @createDate 2022-04-26 17:42:37
* @Entity com.plixiaofei.community.domain.model.Notification
*/
@Mapper
public interface NotificationMapper extends BaseMapper<Notification> {

}




