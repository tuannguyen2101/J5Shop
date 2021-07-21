package com.home.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.home.shop.interceptor.AdminInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(adminInterceptor)
			.addPathPatterns("/admin/**");
//			.excludePathPatterns("admin/login");
	}
}