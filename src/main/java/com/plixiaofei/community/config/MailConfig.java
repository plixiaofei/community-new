package com.plixiaofei.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created on 2022/4/25 by plixiaofei
 */
@Configuration
public class MailConfig {
    @Value("${mail.from}")
    private String from;
    @Value("${mail.to}")
    private String to;

    @Value("${mail.list.rabbitmq-at}")
    private String rabbitmqAt;

    @Bean(value = "mailRabbitmqAt")
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(rabbitmqAt + "<" + from + ">");
        mailMessage.setTo(to);
        return mailMessage;
    }
}
