package com.example.demo.config;

import com.example.demo.entity.Orders;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.demo.repository.redis")  // Redis Repository 활성화
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379); // Redis 호스트 & 포트 설정
    }

//    @Bean
//    public RedisTemplate<String, Orders> redisTemplate(RedisConnectionFactory connectionFactory) {
//        RedisTemplate<String, Orders> template2 = new RedisTemplate<>();
//        template2.setConnectionFactory(connectionFactory);
//        System.out.println("REDISTemplate 빈 생성됨....%%%%%%%%%%%%%%%%%%%%");
//        return template2;
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template2 = new RedisTemplate<>();
        template2.setConnectionFactory(connectionFactory);
        System.out.println("REDISTemplate 빈 생성됨....%%%%%%%%%%%%%%%%%%%%");
        return template2;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))  // cache TTL을 1시간으로 설정, 분단위로 설정 가능!
//                .entryTtl(Duration.ofMinutes(10)) //10분, 초단위도 가능
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                    new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }

//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(2))  // cache 기본 TTL: 2시간
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//        cacheConfigurations.put("shortCache", RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(10)));  // 특정 캐시: 10초 TTL
//        cacheConfigurations.put("longCache", RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(5)));  // 특정 캐시: 5시간 TTL
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .cacheDefaults(defaultCacheConfig)
//                .withInitialCacheConfigurations(cacheConfigurations)
//                .build();
//    }
}
