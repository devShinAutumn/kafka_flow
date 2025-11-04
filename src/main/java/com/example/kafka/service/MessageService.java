package com.example.kafka.service;

import com.example.kafka.model.NotifyRequest;
import com.example.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final KafkaProducer producer;

    @Value("${spring.kafka.template.default-topic:notify-topic}")
    private String topic;

    public void enqueue(NotifyRequest request) {
        producer.send(topic, request);
    }
}
