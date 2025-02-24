package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.NaverRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverService2 {

    private final NaverRepository2 naverRepository;

    public void insertJwt(User user) {
        System.out.println("service ~~~~~~~~~~~~~~~~~~~~");
        System.out.println("user = " + user);
        System.out.println("service ~~~~~~~~~~~~~~~~~~~~");
        naverRepository.save(user);
    }

    public User getFindByUsername(String email) {
        return naverRepository.getFindByEmail(email);
    }
}

