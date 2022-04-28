package com.plixiaofei.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plixiaofei.community.domain.model.Notification;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.exception.CustomException;
import com.plixiaofei.community.service.NotificationService;
import com.plixiaofei.community.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author plixiaofei
* @description 针对表【notification(通知表)】的数据库操作Service实现
* @createDate 2022-04-26 17:42:37
*/
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification>
    implements NotificationService{
    @Value("${page.notification}")
    private int NOTIFICATION_EACH_PAGE;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public void saveNotification(Notification notification) {
        notificationMapper.insert(notification);
    }

    @Override
    public List<Notification> getNotificationByUsername(String username, int curPage) {
        QueryWrapper<Notification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("to_user", username)
                .orderByDesc("create_time");
        Page<Notification> notificationPage = notificationMapper.selectPage(
                new Page<>(curPage, NOTIFICATION_EACH_PAGE), queryWrapper
        );
        if (curPage > notificationPage.getPages()) {
            throw new CustomException(ResultCode.PAGE_OUT_OF_RANGE);
        }
        return notificationPage.getRecords();
    }
}
