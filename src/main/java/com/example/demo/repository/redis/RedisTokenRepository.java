package com.example.demo.repository.redis;

import com.example.demo.entity.RedisToken;
import com.example.demo.entity.RedisUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}

