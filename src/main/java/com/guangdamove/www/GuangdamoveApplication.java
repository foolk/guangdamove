package com.guangdamove.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GuangdamoveApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GuangdamoveApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //参数为当前springboot启动类
        //构造新资源
        return builder.sources(GuangdamoveApplication.class);
    }
}
