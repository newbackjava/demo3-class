package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaverRepository2
        extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getFindByEmail(String username);
}


