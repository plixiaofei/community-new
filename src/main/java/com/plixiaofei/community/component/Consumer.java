package com.plixiaofei.community.component;

import com.plixiaofei.community.domain.model.Notification;
import com.plixiaofei.community.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Map;

/**
 * 消费队列
 * Created on 2022/4/25 by plixiaofei
 */
@RabbitListener(queues = "at.queue")
@Component
public class Consumer {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage mailRabbitmqAt;
    @Autowired
    private NotificationService notificationService;


    @RabbitHandler
    public void process(String message) {
        System.out.println("消费" + message);
//        mailRabbitmqAt.setSubject("消息提示");
//        mailRabbitmqAt.setText("消息提示");
//        mailSender.send(mailRabbitmqAt);
    }
    @RabbitHandler
    public void process(Map<String, Object> message) {
        System.out.println("消费Map" + message);
        message.forEach((k, v) -> System.out.println(v.getClass()));
        Notification notification = new Notification();
        notification.setCreateTime(new Date(System.currentTimeMillis()));
        notification.setQuestionId(new Long((Integer) message.get("questionId")));
        notification.setCommentId(new Long((Integer) message.get("commentId")));
        notification.setFromUser((String) message.get("fromUser"));
        notification.setToUser((String) message.get("toUser"));
        notification.setContent((String) message.get("content"));
        System.out.println(notification);
        notificationService.saveNotification(notification);
    }
}
