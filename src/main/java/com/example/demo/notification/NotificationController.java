package com.example.demo.notification;

import com.example.demo.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * NotificationController 클래스는 알림 관련 HTTP 요청을 처리하는 컨트롤러입니다.
 * WebSocket을 사용하여 클라이언트와 서버 간의 실시간 통신을 제공합니다.
 */
@Controller
@RequestMapping("notification") // 이 컨트롤러의 모든 메소드의 기본 URL 경로를 '/notification'으로 설정합니다.
public class NotificationController {

    @Autowired
    private NotificationService notificationService; // NotificationService를 자동 주입하여 사용합니다.

    /**
     * 알림 페이지의 홈 경로를 처리합니다.
     *
     * @return Thymeleaf를 사용한 뷰의 이름을 반환하여 알림 페이지를 렌더링합니다.
     */
    @GetMapping
    public String home() {
        // 반환하는 문자열은 'resources/templates/notification/notification.s3.html'에 해당하는 Thymeleaf 뷰입니다.
        return "notification/notification";
    }

    /**
     * WebSocket 메시지를 받고 이를 다시 모든 구독자에게 전송합니다.
     *
     * @param message 클라이언트로부터 받은 메시지
     * @return 전송할 메시지를 반환하여 '/topic/notification' 주제로 구독하고 있는 클라이언트들에게 메시지를 전파합니다.
     */
    @MessageMapping("/send") // WebSocket을 통해 "/app/send" 경로로 메시지를 받음
    @SendTo("/topic/notification") // 받은 메시지를 "/topic/notification" 주제의 구독자에게 전송
    public String sendNotification(String message) {
        notificationService.sendNotification(message); // 받은 메시지를 NotificationService를 통해 처리
        return message; // WebSocket 구독자에게 메시지를 보냄
    }
}
