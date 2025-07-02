package com.notification.notification_service.listner;

import com.notification.notification_service.dto.NotificationMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(NotificationMessage message) {
        System.out.println("🔔 Notification received:");
        System.out.println("Service: " + message.getService());
        System.out.println("Action: " + message.getAction());
        System.out.println("Message: " + message.getMessage());
        System.out.println("Timestamp: " + message.getTimestamp());
    }
}