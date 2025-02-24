package com.example.demo.controller;

import com.example.demo.entity.RedisToken;
import com.example.demo.entity.RedisUser;
import com.example.demo.service.RedisTokenService;
import com.example.demo.service.RedisUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/token")
@RequiredArgsConstructor
public class RedisTokenController {

    private final RedisTokenService redisTokenService;

    @GetMapping("token")
    public String redis() {
        return "redis/token";
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseEntity<RedisToken> saveUser(@RequestBody RedisToken token) {
        return ResponseEntity.ok(redisTokenService.saveToken(token));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RedisToken> getUser(@PathVariable String id) {
        Optional<RedisToken> token = redisTokenService.getToken(id);
        return token.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
