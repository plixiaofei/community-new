package com.plixiaofei.community.controller;

import co.elastic.clients.elasticsearch.nodes.Http;
import com.plixiaofei.community.domain.model.Notification;
import com.plixiaofei.community.domain.model.Result;
import com.plixiaofei.community.enumeration.ResultCode;
import com.plixiaofei.community.service.NotificationService;
import com.plixiaofei.community.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 2022/4/27 by plixiaofei
 */
@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/api/getNotification", method = RequestMethod.GET)
    public Result<Object> getNotification(HttpServletRequest request,
                                          @RequestParam("curPage") int curPage) {
        String authorization = request.getHeader("Authorization").split("\"")[1];
        String username = JwtUtil.getTokenClaims(authorization).get("username").asString();
        List<Notification> notificationList = notificationService.getNotificationByUsername(username, curPage);
        return new Result<>(ResultCode.SUCCESS, notificationList);
    }
    @RequestMapping(value = "/api/commentExists", method = RequestMethod.GET)
    public Result<Object> commentExists(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization").split("\"")[1];
        String username = JwtUtil.getTokenClaims(authorization).get("username").asString();
        System.out.println(redisTemplate.opsForHash().get("NotifyCenter", "sddas"));
        return new Result<>(ResultCode.SUCCESS, redisTemplate.opsForHash().get("NotifyCenter", username));
    }
}
