package com.example.demo;

import com.example.demo.entity.RedisUser;
import com.example.demo.service.RedisUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUserServiceTest {

    @Autowired
    private RedisUserService redisUserService;

    @Test
    void testSaveUser() {
        /*
        RedisUser user = new RedisUser("1", "Alice", 25);
        redisUserService.saveUser(user);
        */

        RedisUser retrievedUser = redisUserService.getUser("Alice").orElse(null);
        System.out.println("====================================");
        System.out.println(retrievedUser);
        System.out.println("====================================");
        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
        assertEquals(25, retrievedUser.getAge());
    }
}
