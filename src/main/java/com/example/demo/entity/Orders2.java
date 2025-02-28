package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Data
@RedisHash("orders") // Redis의 Key 값
@NoArgsConstructor
@AllArgsConstructor
public class Orders2 implements Serializable{

    @Id
    private Long id;
    private String itemName;
    private int quantity;

    @TimeToLive
    private Long ttl = 10L; // TTL 설정 (10초)
}