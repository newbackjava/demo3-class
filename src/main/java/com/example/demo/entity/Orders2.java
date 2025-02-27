package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("orders") // Redis의 Key 값
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders2 implements Serializable{

    private Long id;
    private String itemName;
    private int quantity;

    // Getters and Setters
}