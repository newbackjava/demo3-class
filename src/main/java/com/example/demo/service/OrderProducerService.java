package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@RequiredArgsConstructor
public class OrderProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    //public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate) {
      //  this.kafkaTemplate = kafkaTemplate;
    //}

    public void sendOrder(String orderDetails) {
        kafkaTemplate.send("order2-topic", orderDetails);
        System.out.println("@@@@@@@@@@@@@@ Order sent to Kafka: " + orderDetails);
    }
}


//////////// kafka는 spring에서 kafka까지의 전송은 비동기
/// 전송도 동기식으로 하게 하려면 다음과 같이 수정, 도착된 것을 확인하고 다음 전송을 함.
/// 전송은 비동기, kafka queue에 쌓인 것은 동기처리
//  전송이 순서대로 되지 않으므로(톰킷이 멀티스레드 환경), queue에 쌓인 것이 이미 순서대로 들어와있지 않음.
//import org.springframework.beans.factory.annotation.Autowired;  // Spring 프레임워크의 자동 의존성 주입 기능을 사용하기 위한 애너테이션
//import org.springframework.kafka.core.KafkaTemplate;           // Kafka 메시지를 전송하기 위한 Spring의 템플릿 클래스
//import org.springframework.kafka.support.SendResult;          // Kafka로 메시지를 전송한 결과를 나타내는 클래스
//import org.springframework.util.concurrent.ListenableFuture;  // 비동기 결과를 처리할 수 있는 Spring의 퓨처 클래스
//
//public class KafkaProducer {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;      // KafkaTemplate 인스턴스를 자동으로 주입받는 필드
//
//    // Kafka에 메시지를 동기식으로 전송하는 메소드
//    public void sendMessageSync(String topic, String message) {

//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//        // KafkaTemplate를 사용하여 메시지를 비동기적으로 전송하고 결과를 ListenableFuture 객체로 반환받음
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
//        try {

//            // ListenableFuture에서 get() 메소드를 호출하여 Kafka 응답을 동기적으로 대기하고 결과를 받음
//            SendResult<String, String> sendResult = future.get(); // 동기적으로 결과를 기다림
//            // 메시지 전송 성공을 콘솔에 출력
//            System.out.println("Message sent successfully: " + sendResult.getRecordMetadata());
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

//        } catch (InterruptedException | ExecutionException e) { // get() 메소드에서 발생할 수 있는 예외 처리
//            // 메시지 전송 실패 시 에러 메시지를 콘솔에 출력
//            System.err.println("Failed to send message: " + e.getMessage());
//            // 현재 스레드를 인터럽트하여 예외 상황을 처리
//            Thread.currentThread().interrupt();
//        }
//    }
//}
