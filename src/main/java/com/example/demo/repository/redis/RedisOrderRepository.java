package com.example.demo.repository.redis;

import com.example.demo.entity.Orders2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisOrderRepository extends CrudRepository<Orders2, String> {
}
