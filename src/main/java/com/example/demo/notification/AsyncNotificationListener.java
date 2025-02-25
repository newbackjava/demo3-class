package com.example.demo.notification;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncNotificationListener {

    @Async
    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        System.out.println("Asynchronously handling event: " + event.getMessage());
    }
}
