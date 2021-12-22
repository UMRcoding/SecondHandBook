package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Copyright (C), 2021-2021
 * FileName: SpringBootStarterApplication
 * Author: Liu Heng
 * Date: 2021/12/22 15:50
 * Description:
 * History:
 * Liu Heng <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

public class SpringBootStarterApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
