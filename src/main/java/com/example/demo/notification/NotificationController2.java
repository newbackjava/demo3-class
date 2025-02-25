package com.example.demo.notification;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notification2")
public class NotificationController2 {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public String notification(Model model) {
        return "notification/notification2";
    }

    @MessageMapping("/send2")
    @SendTo("/topic/notification2")
    public String sendNotification(NotificationEvent2 message) {
        return message.getMessage();
    }
}
