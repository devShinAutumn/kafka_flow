package com.example.kafka.model;

import java.util.Map;
import lombok.Data;

@Data
public class NotifyRequest {
    private String type;        // "email" or "kakao"
    private String to;          // email address or phone number
    private String subject;     // email subject
    private String template;    // template key (e.g., "order" or "alert")
    private Map<String, Object> variables; // template variables
}
