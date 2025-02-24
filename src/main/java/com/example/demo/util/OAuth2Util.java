package com.example.demo.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OAuth2Util {

    public static void pushToken(String email){
        // ✅ Spring Security 권한 설정 (DB 없이 JWT 정보만 사용)
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

        // ✅ UserDetails 객체 생성 (임의 생성, DB 사용 X)
        UserDetails userDetails = User.withUsername(email)
                .password("[PROTECTED]") // 패스워드는 필요 없으므로 더미 값 사용
                .authorities(authorities)
                .build();
    } //
}
