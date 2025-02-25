package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "RedisToken", timeToLive = 20L)
@Data
public class RedisToken {


    @Id
    private String id;
    private String name;
    private int age;

    public RedisToken() {
    }

    public RedisToken(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
