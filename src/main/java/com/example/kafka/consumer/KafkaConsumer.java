package com.example.kafka.consumer;

import com.example.kafka.model.NotifyRequest;
import com.example.kafka.service.EmailService;
import com.example.kafka.service.KakaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;
    private final KakaoService kakaoService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "notify-topic", groupId = "notify-group")
    public void consume(String message) {
        try {
            NotifyRequest req = objectMapper.readValue(message, NotifyRequest.class);
            switch (req.getType()) {
                case "email" -> emailService.send(req);
                case "kakao" -> kakaoService.send(req);
                default -> log.warn("Unknown type: {}", req.getType());
            }
        } catch (Exception e) {
            log.error("Failed to consume message: {}", message, e);
        }
    }
}
