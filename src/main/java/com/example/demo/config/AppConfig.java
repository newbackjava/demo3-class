package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ImportResource("classpath:applicationContext.xml")
public class AppConfig {
    // XML 설정이 로드됨

    //모든 post방식으로 전달된 데이터 한글 필터링 처리함.
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}

//여러개의 파일일 때
//@ImportResource({
//        "classpath:applicationContext.xml",
//        "classpath:databaseConfig.xml",
//        "classpath:securityConfig.xml"
//})