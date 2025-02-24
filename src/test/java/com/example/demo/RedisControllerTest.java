package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class RedisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET 요청 테스트 - JSON 응답 검증")
    public void testRedisEndpoint() throws Exception {
        // Given: Redis에 "1"번 ID를 가진 사용자 저장 (사전 테스트 데이터 필요)
        String expectedJson = """
            {
                "id": "1",
                "name": "Alice",
                "age": 25
            }
        """;

        // When & Then: GET 요청 실행 후 JSON 응답 검증
        mockMvc.perform(get("/redis/1"))
                .andExpect(status().isOk())  // HTTP 상태 코드 200 확인
                .andExpect(content().contentType("application/json")) // JSON 응답 확인
                .andExpect(jsonPath("$.id").value("1"))  // JSON 응답 필드 검증
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    @DisplayName("GET 요청 테스트 - 존재하지 않는 사용자")
    public void testRedisEndpoint_UserNotFound() throws Exception {
        // When & Then: 존재하지 않는 사용자 조회 시 404 응답 확인
        mockMvc.perform(get("/redis/99"))
                .andExpect(status().isNotFound());
    }
}