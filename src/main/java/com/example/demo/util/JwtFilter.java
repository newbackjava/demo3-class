package com.example.demo.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // ✅ JWT 유틸리티 클래스

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // ✅ SecurityContext에 이미 인증 정보가 있는 경우 JWT 검증 생략
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // ✅ 쿠키에서 JWT 토큰 추출
            Optional<String> tokenOptional = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                    .filter(cookie -> "accessToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst();

            if (tokenOptional.isPresent()) {
                String token = tokenOptional.get();

                if (jwtUtil.validateToken(token)) {
                    // ✅ JWT에서 사용자명과 역할(Role) 추출
                    String username = jwtUtil.getUsernameFromToken(token);
                    String role = jwtUtil.getRoleFromToken(token);

                    // ✅ Spring Security 권한 설정 (DB 없이 JWT 정보만 사용)
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

                    // ✅ UserDetails 객체 생성 (임의 생성, DB 사용 X)
                    UserDetails userDetails = User.withUsername(username)
                            .password("[PROTECTED]") // 패스워드는 필요 없으므로 더미 값 사용
                            .authorities(authorities)
                            .build();

                    // ✅ Authentication 객체 생성 후 SecurityContextHolder에 저장
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // ✅ 다음 필터로 진행
        chain.doFilter(request, response);
    }
}
