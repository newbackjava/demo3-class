package com.example.demo;


import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisConnectTest {

    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;
    private static RedisCommands<String, String> syncCommands;

    @BeforeAll
    static void setup() {
        redisClient = RedisClient.create("redis://localhost:6379");
        connection = redisClient.connect();
        syncCommands = connection.sync();
    }

    @Test
//    @Order(1)
    void testSaveData() {
        syncCommands.set("name", "Alice");
        syncCommands.set("age", "25");

        assertEquals("Alice", syncCommands.get("name"));
        assertEquals("25", syncCommands.get("age"));
    }

    @Test
//    @Order(2)
    void testDeleteData() {
        syncCommands.del("name", "age");

        assertNull(syncCommands.get("name"));
        assertNull(syncCommands.get("age"));
    }

    @AfterAll
    static void tearDown() {
        connection.close();
        redisClient.shutdown();
    }
}
