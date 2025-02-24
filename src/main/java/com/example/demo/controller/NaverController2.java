package com.example.demo.controller;

import com.example.demo.dto.NaverDto;
import com.example.demo.service.NaverService2;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

import java.util.Map;


@Controller
@RequiredArgsConstructor
@Value
@RequestMapping("naver2")
public class NaverController2 {

    private BCryptPasswordEncoder encoder;
    private NaverService2 naverService;
    private JwtUtil jwtUtil;
    private AuthController authController;

    @GetMapping("login")
    public String login() {
        return "naver/login";
    }

    @PostMapping("signUp")
    public String singUp(@RequestBody  User user2, Model model, HttpServletResponse response) {
        System.out.println("#############################");
        System.out.println("user2.username>>> "  + user2.getUsername());
        System.out.println("user2.password>>> "  + user2.getPassword());
        System.out.println("#############################");
        User registerCheck = naverService.getFindByUsername(user2.getUsername());
        System.out.println("#############################");
        System.out.println(registerCheck);
        System.out.println("#############################");
        if (registerCheck == null) {
            User user = User.builder()
                    .username(user2.getUsername())
                    .password(encoder.encode(user2.getPassword()))
                    .role("ROLE_USER")
                    .build();
            System.out.println("########################");
            System.out.println("username = " + user.getUsername());
            System.out.println("password = " + user.getPassword());
            System.out.println("role = " + user.getRole());
            System.out.println("########################");

            naverService.insertJwt(user);

            // ✅ JWT 토큰 생성
            String token = jwtUtil.generateToken(user2.getUsername(), "ROLE_USER");
            System.out.println("########################");
            System.out.println("token = " + token);
            System.out.println("########################");

            // ✅ HTTPOnly, Secure 쿠키에 JWT 저장
/// /////////////////////////////// 실습 부분 //////////////////////////////////////////////
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // HTTPS 환경에서만 전송
            cookie.setPath("/");
            cookie.setMaxAge(86400); // 1일 (초 단위)
            response.addCookie(cookie);
/// /////////////////////////////// 실습 부분 //////////////////////////////////////////////
        }
        model.addAttribute("naverDto", user2);

        return "naver/singUp2";
    }

    @GetMapping("/emailCheck/{email}")
    @ResponseBody
    public boolean emailCheck(@PathVariable String email, Model model, HttpServletResponse response) {
        boolean result = false;
        User user = naverService.getFindByUsername(email);
        System.out.println("################");
        System.out.println("username >> " + user.getUsername());
        System.out.println("userpass >> " + user.getPassword());
        System.out.println("################");
        if (user != null) {
            result = true;
            /// ////////////
            /// 인증하고 token발급 call
            /// ////////////
            Map<String, String> mapResult = authController.getToken2(response, user.getUsername(), user.getRole());
            System.out.println("============================");
            System.out.println("mapResult>>> " + mapResult);
            System.out.println("============================");
        }
        return result;
    }


}
