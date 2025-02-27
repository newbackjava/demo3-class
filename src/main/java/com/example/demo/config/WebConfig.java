package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Spring IoC 컨테이너에 의해 관리되는 설정 클래스를 선언
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // '/upload/**' URL 패턴으로 들어오는 요청을 처리하기 위한 리소스 핸들러를 등록
        registry.addResourceHandler("/upload/**")
                // 파일 시스템의 특정 경로에서 리소스를 제공하는 위치를 설정
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static/upload/");
    }
}