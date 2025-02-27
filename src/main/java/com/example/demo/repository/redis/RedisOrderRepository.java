package com.example.demo.repository.redis;

import com.example.demo.entity.Orders2;
import com.example.demo.entity.RedisToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisOrderRepository extends CrudRepository<Orders2, String> {
}
