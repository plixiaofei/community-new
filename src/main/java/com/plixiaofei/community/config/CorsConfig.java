package com.plixiaofei.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Created on 2022/4/8 by plixiaofei
 */
@Configuration
public class CorsConfig {
    @Value("${front}")
    private String FRONT;
    @Value("${front_test}")
    private String FRONT_TEST;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(FRONT);
        configuration.addAllowedOrigin(FRONT_TEST);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
