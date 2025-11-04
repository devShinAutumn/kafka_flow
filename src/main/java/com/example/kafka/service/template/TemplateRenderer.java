package com.example.kafka.service.template;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TemplateRenderer {

    public String render(String path, Map<String, Object> variables) {
        try {
            ClassPathResource res = new ClassPathResource(path);
            try (InputStream is = res.getInputStream()) {
                String content = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                        .lines().collect(Collectors.joining("
"));
                if (variables != null) {
                    for (Map.Entry<String, Object> e : variables.entrySet()) {
                        content = content.replace("{{" + e.getKey() + "}}", String.valueOf(e.getValue()));
                    }
                }
                return content;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to render template: " + path, e);
        }
    }
}
