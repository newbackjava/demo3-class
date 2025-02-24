package com.example.demo.test;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisTest2 {
    public static void main(String[] args) {
        // Redis 클라이언트 생성 (Docker의 Redis 컨테이너에 연결)
        String redisUri = "redis://localhost:6379";
        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        // 데이터 저장
        syncCommands.set("name", "Alice");
        syncCommands.set("age",  "25");

        // 데이터 조회
        String name = syncCommands.get("name");
        String age = syncCommands.get("age");

        System.out.println("🔹 Redis에서 가져온 데이터:");
        System.out.println("이름: " + name);
        System.out.println("나이: " + age);

        // 데이터 삭제
       // syncCommands.del("name", "age");

        // 연결 종료
        connection.close();
        redisClient.shutdown();
    }
}
