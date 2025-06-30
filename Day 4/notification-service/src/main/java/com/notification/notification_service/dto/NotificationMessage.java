package com.notification.notification_service.dto;

import java.time.LocalDateTime;
import lombok.Data;


@Data
public class NotificationMessage {
    private String service;
    private String action;
    private String message;
    private LocalDateTime timestamp;
}