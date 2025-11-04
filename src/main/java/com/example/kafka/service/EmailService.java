package com.example.kafka.service;

import com.example.kafka.model.NotifyRequest;
import com.example.kafka.service.template.TemplateRenderer;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateRenderer renderer;

    @Value("${notify.email.from:no-reply@example.com}")
    private String from;

    public void send(NotifyRequest req) {
        try {
            String template = req.getTemplate() == null ? "order" : req.getTemplate();
            String html = renderer.render("templates/email/" + template + ".html", req.getVariables());

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(req.getTo());
            helper.setSubject(req.getSubject() == null ? "Notification" : req.getSubject());
            helper.setText(html, true);

            mailSender.send(message);
            log.info("Email sent to {}", req.getTo());
        } catch (Exception e) {
            log.error("Failed to send email", e);
        }
    }
}
