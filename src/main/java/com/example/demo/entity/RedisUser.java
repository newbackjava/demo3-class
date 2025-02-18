package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@RedisHash(value = "RedisUser", timeToLive = 20L)
@Data
public class RedisUser implements Serializable {

    @Id
    private String id;
    private String name;
    private int age;

    public RedisUser() {}

    public RedisUser(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
