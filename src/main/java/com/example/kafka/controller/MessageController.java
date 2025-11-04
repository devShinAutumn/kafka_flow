package com.example.kafka.controller;

import com.example.kafka.model.NotifyRequest;
import com.example.kafka.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notify")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public String notify(@RequestBody NotifyRequest request) {
        messageService.enqueue(request);
        return "Enqueued";
    }
}
