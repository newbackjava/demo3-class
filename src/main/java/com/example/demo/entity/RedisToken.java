package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "RedisToken", timeToLive = 20L)
@Data
@AllArgsConstructor
public class RedisToken implements Serializable {

    @Id
    private String id; //accessToken
    private String refreshToken;
}
