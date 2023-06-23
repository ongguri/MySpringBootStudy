package com.example.testproject1.config;

import com.example.testproject1.interceptor.HttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()) // HttpInterceptor 인터셉터 등록
                .addPathPatterns("/**") // 해당 인터셉터가 동작할 경로 설정
                .excludePathPatterns("/hello"); // 설정된 경로는 인터셉터 예외 설정
    }
}
