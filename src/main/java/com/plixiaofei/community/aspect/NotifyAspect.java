package com.plixiaofei.community.aspect;

import com.plixiaofei.community.component.Producer;
import com.plixiaofei.community.domain.dto.CommentDTO;
import com.plixiaofei.community.domain.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2022/4/26 by plixiaofei
 */
@Component
@Slf4j
@Aspect
public class NotifyAspect {
    @Autowired
    private Producer producer;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution (* com.plixiaofei.community.service.impl.CommentServiceImpl.saveComment(..)) ")
    public void notifyCenter() {
    }

    @Around("notifyCenter()")
    public Object notifyUser(ProceedingJoinPoint point) throws Throwable {
        log.info("开始执行");
        Object[] args = point.getArgs();
        String fromUser = (String) args[0];
        CommentDTO commentDTO = (CommentDTO) args[1];
        // 处理 content 中的 @
        String content = commentDTO.getContent();
        Object proceed = point.proceed(args);
        CommentVO commentVO = (CommentVO) proceed;
        if (!Objects.equals(commentVO.getFromUsername(), fromUser)) {
            if (content.contains("@")) {
                Pattern compile = Pattern.compile("@(.*?) ");
                Matcher matcher = compile.matcher(content);
                while (matcher.find()) {
                    Map<String, Object> hashMap = new HashMap<>();
                    hashMap.put("questionId", commentDTO.getQuestionId());
                    hashMap.put("fromUser", fromUser);
                    String toUser = matcher.group(1);
                    hashMap.put("toUser", toUser);
                    redisTemplate.opsForHash().put("NotifyCenter", toUser, "1");
                    hashMap.put("content", commentDTO.getContent());
                    hashMap.put("commentId", commentVO.getId());
                    producer.sendAt(hashMap);
                }
            }
        }
        return proceed;
    }

    @Pointcut("execution (* com.plixiaofei.community.service.impl.CommentServiceImpl.saveComment(..))")
    public void replyCenter() {}

    @Around("replyCenter()")
    public Object replyUser(ProceedingJoinPoint point) throws Throwable {
        log.info("执行 replyUser");
        Object[] args = point.getArgs();
        String fromUsername  = (String) args[0];
        CommentDTO commentDTO = (CommentDTO) args[1];
        Object proceed = point.proceed(args);
        System.out.println(proceed);
        log.info("结束 replyUser");
        return proceed;
    }
}
