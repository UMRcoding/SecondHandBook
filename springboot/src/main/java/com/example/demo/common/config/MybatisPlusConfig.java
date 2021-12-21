package com.example.demo.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: mybatis-plus 分页插件
 * @Author: Liu Heng
*/

@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfig {

    /**
    * 功能描述: 分页插件
    * @Param: []
    * @Author: Liu Heng
    * @return: com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
    */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
