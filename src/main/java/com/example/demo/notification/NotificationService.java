package com.example.demo.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * NotificationService 클래스는 애플리케이션 전반에서 사용할 알림 이벤트를 관리하는 서비스입니다.
 * 스프링의 ApplicationEventPublisher를 사용하여 이벤트를 발행합니다.
 */
@Service  // 스프링 컨텍스트가 이 클래스를 서비스 빈으로 관리하도록 설정
public class NotificationService {

    @Autowired  // 스프링의 의존성 주입 기능을 사용하여 ApplicationEventPublisher 인스턴스를 자동으로 주입
    private ApplicationEventPublisher publisher;

    /**
     * sendNotification 메서드는 주어진 메시지를 포함하는 NotificationEvent를 생성하고 발행합니다.
     *
     * @param message 발행할 이벤트에 포함할 문자열 메시지
     */
    public void sendNotification(String message) {
        // NotificationEvent 객체를 생성, this는 이벤트 소스로 현재 NotificationService 인스턴스를 사용
        NotificationEvent event = new NotificationEvent(this, message);
        // 스프링의 이벤트 퍼블리셔를 통해 생성된 이벤트를 발행
        publisher.publishEvent(event);
    }
}


//이 클래스는 @Service 어노테이션을 사용하여 스프링에 의해 관리되는 서비스 레이어의 컴포넌트임을 나타냄.
//@Autowired 어노테이션을 사용하여 ApplicationEventPublisher를 자동 주입받음.
//이를 통해 NotificationEvent 객체를 생성하고 발행하는 작업을 수행함.
//이렇게 발행된 이벤트는 애플리케이션 내 다른 컴포넌트에서 수신하고 반응할 수 있음.
//이를 통해 애플리케이션의 다양한 부분에서 비동기적으로 정보를 교환하고 반응할 수 있는 구조를 제공
