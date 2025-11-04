package com.example.kafka.service;

import com.example.kafka.model.NotifyRequest;
import com.example.kafka.service.template.TemplateRenderer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;

/**
 * 테스트용 카카오톡 발송 시뮬레이터
 * 실제 API 호출 대신, 요청/응답 로그 출력 및 가상 응답 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final TemplateRenderer renderer;

    public void send(NotifyRequest req) {
        try {
            String template = req.getTemplate() == null ? "alert" : req.getTemplate();
            String body = renderer.render("templates/kakao/" + template + ".txt", req.getVariables());

            // 시뮬레이션 API endpoint
            String mockUrl = "https://mock.kakao-api.local/send";

            log.info("[KAKAO-TEST] Sending message to {}...", req.getTo());
            log.info("Mock POST {}", mockUrl);


            URL url = new URL(mockUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            String payload = String.format("{\"to\":\"%s\",\"body\":\"%s\"}", req.getTo(), body.replace("\n", "\\n"));
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            int status = 200; // 테스트용 가상 응답
            log.info("[KAKAO-TEST] Response code: {}", status);
            log.info("[KAKAO-TEST] Message body:\n{}", body);

        } catch (Exception e) {
            log.error("Mock Kakao send failed", e);
        }
    }
}