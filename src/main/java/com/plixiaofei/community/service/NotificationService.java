package com.plixiaofei.community.service;

import com.plixiaofei.community.domain.model.Notification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author plixiaofei
* @description 针对表【notification(通知表)】的数据库操作Service
* @createDate 2022-04-26 17:42:37
*/
public interface NotificationService extends IService<Notification> {
    void saveNotification(Notification notification);

    List<Notification> getNotificationByUsername(String username, int curPage);
}
