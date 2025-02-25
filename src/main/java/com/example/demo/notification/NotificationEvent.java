package com.example.demo.notification;

import org.springframework.context.ApplicationEvent;

/**
 * NotificationEvent 클래스는 사용자 정의 이벤트를 정의합니다.
 * 이 클래스는 스프링의 ApplicationEvent를 상속받아 이벤트 퍼블리싱 기능을 활용할 수 있도록 합니다.
 * 스프링의 이벤트 메커니즘을 사용하여 애플리케이션의 다른 부분에 메시지를 전달하는 데 사용됩니다.
 */
public class NotificationEvent extends ApplicationEvent {
    // 이벤트와 관련된 메시지를 저장하는 필드
    private String message;

    /**
     * NotificationEvent 생성자.
     *
     * @param source 이벤트의 발생 소스, 일반적으로 이벤트를 발행하는 객체
     * @param message 이벤트와 관련된 추가 정보를 담은 메시지 문자열
     */
    public NotificationEvent(Object source, String message) {
        super(source);  // 부모 클래스의 생성자를 호출, 이벤트의 소스를 초기화
        this.message = message;  // 이벤트 메시지를 초기화
    }

    /**
     * 이벤트의 메시지를 반환하는 메소드.
     *
     * @return 이벤트와 관련된 메시지 문자열
     */
    public String getMessage() {
        return message;
    }
}
