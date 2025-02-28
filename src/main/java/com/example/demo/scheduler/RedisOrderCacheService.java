package com.example.demo.scheduler;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Orders2;
import com.example.demo.repository.MySqlOrderRepository;
import com.example.demo.repository.redis.RedisOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RedisOrderCacheService {
    private final MySqlOrderRepository orderRepository; // MySQL에서 주문 데이터를 가져오는 Repository
    private final RedisOrderRepository redisOrderRepository; // Redis에 주문 데이터를 저장하는 Repository

    //@Scheduled(cron = "0 0 0 * * SUN")  // 매주 일요일 자정에 실행
    @Scheduled(cron = "0 0/1 * * * ?") // 1분마다 실행되는 스케줄러
    public void updateOrderCache() {
        System.out.println("======= Scheduled 시작!!  ========");

        // MySQL에서 최근 100개의 주문 데이터 가져오기
        List<Orders> recentOrders = orderRepository.findTop100ByOrderByCreatedAtDesc();
        System.out.println(recentOrders.size() + "----------------------");

        // ID가 존재하는 데이터만 필터링 후 Orders → Orders2로 변환
        List<Orders2> validOrders = recentOrders.stream()
                .filter(order -> order.getId() != null)
                .map(this::convertToOrders2) // 변환 메서드 추가
                .collect(Collectors.toList());

        // 기존 Redis 캐시 삭제
        // redisOrderRepository.deleteAll();

        // 새로운 주문 데이터 Redis에 저장
        redisOrderRepository.saveAll(validOrders);

        System.out.println("기존 캐시 삭제 후 새 데이터 삽입함.");

        // Redis에 저장된 데이터 개수 확인
        System.out.println("======= 재검색함 ========");
        System.out.println(redisOrderRepository.count());
        redisOrderRepository.findAll().forEach(redisOrder -> {
            System.out.println("-----> " + redisOrder);
        });
    }

    // Orders → Orders2 변환 메서드
    private Orders2 convertToOrders2(Orders order) {
        return new Orders2(
                order.getId(),
                order.getItemName(),
                order.getQuantity(),
                null // TTL은 엔티티 기본값(1시간)으로 자동 설정됨
        );
    }
}
