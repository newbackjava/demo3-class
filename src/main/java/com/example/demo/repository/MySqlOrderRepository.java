package com.example.demo.repository;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySqlOrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o order by o.id desc limit 100")
    List<Orders> findTop100ByOrderByCreatedAtDesc();
}
