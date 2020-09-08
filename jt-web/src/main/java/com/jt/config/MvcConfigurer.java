package com.jt.config;

import com.jt.handler.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tarena
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer{
	@Autowired
	private UserInterceptor userInterceptor;
	
	/**开启匹配后缀型配置 xxxx.html    xxxx.do  xxxxx.action
	 * */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		
		configurer.setUseSuffixPatternMatch(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//暂时只拦截购物车/订单模块的请求    /* 只拦截一级请求路径    /**  拦截多级请求路径
		registry.addInterceptor(userInterceptor)
				.addPathPatterns("/cart/**","/order/**");

	}
}
