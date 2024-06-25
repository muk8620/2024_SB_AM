package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeActionInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private BeforeActionInterceptor beforActionInterceptor;
	
	public WebConfig(BeforeActionInterceptor beforActionInterceptor) {
		this.beforActionInterceptor = beforActionInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");
	}
}
