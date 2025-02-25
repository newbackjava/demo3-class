package com.example.demo.service;

import com.example.demo.entity.RedisToken;
import com.example.demo.entity.RedisUser;
import com.example.demo.repository.redis.RedisTokenRepository;
import com.example.demo.repository.redis.RedisUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final RedisTokenRepository redisTokenRepository;

    public RedisToken saveToken(RedisToken id) {
        return redisTokenRepository.save(id);
    }

    public Optional<RedisToken> getToken(String id) {
        return redisTokenRepository.findById(id);
    }
}
