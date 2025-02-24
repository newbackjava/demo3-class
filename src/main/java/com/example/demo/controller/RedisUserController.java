package com.example.demo.controller;

import com.example.demo.entity.RedisUser;
import com.example.demo.service.RedisUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisUserController {

    private final RedisUserService redisUserService;

    @GetMapping("redis")
    public String redis() {
        return "redis/redis";
    }

    @GetMapping("redis2")
    public String redis2() {
        return "redis/redis2";
    }

    @PostMapping("save")
    @ResponseBody
    public ResponseEntity<RedisUser> saveUser(@RequestBody RedisUser user) {
        return ResponseEntity.ok(redisUserService.saveUser(user));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RedisUser> getUser(@PathVariable String id) {
        Optional<RedisUser> user = redisUserService.getUser(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        redisUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
