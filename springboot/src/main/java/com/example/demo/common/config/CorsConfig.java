package com.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * 功能描述: 跨域请求
 * @Author: Liu Heng
*/
@Configuration
public class CorsConfig {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    private CorsConfiguration buildConfig() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 1 设置访问源地址
        corsConfiguration.addAllowedOrigin("*");

        // 2 设置访问源请求头
        corsConfiguration.addAllowedHeader("*");

        // 3 设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");

        corsConfiguration.setMaxAge(MAX_AGE);
        return corsConfiguration;
    }

    /**
    * 功能描述:
    * @Param: []
    * @Author: Liu Heng
    * @return: org.springframework.web.filter.CorsFilter
    */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 4 对接口配置跨域设置
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
