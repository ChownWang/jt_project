package com.jt.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Description TODO
 * @Author ChownWang
 * @Date 2020/8/1 11:29
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /** 将分页的拦截器交给spring容器管理.*/
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
