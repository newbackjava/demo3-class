package com.example.demo.service;

import com.example.demo.entity.RedisUser;
import com.example.demo.repository.redis.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisUserService {

    private final RedisUserRepository redisUserRepository;

    public RedisUser saveUser(RedisUser user) {
        return redisUserRepository.save(user);
    }

    public Optional<RedisUser> getUser(String id) {
        return redisUserRepository.findById(id);
    }

    public void deleteUser(String id) {
        redisUserRepository.deleteById(id);
    }
}
