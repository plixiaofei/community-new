package com.plixiaofei.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on 2022/4/11 by plixiaofei
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Value("${storage.user.avatar}")
    private String userAvatar;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/user/avatar/**")
                .addResourceLocations("file:" + userAvatar);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
