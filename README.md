# Kafka Notify System

Kafka 기반의 비동기 메시징 구조를 활용하여 **이메일(AWS SES)** 과 **카카오톡 알림(Mock)** 을 처리하는 알림 서비스입니다.  
REST API 요청을 Kafka로 전달하고, Consumer가 메시지를 구독해 비동기 방식으로 발송을 수행하도록 구성했습니다.

---

## 구성

| 구성 요소 | 설명 |
|------------|------|
| **Producer** | 클라이언트 요청을 받아 Kafka Topic(`notify-topic`)으로 메시지 전송 |
| **Consumer** | Kafka 메시지를 구독해 이메일 또는 카카오 발송 처리 |
| **Email Service** | Amazon SES SMTP를 이용한 이메일 발송 |
| **Kakao Service** | 테스트용 Mock 발송 (실제 API 연동 전용) |
| **Template Engine** | 간단한 변수 치환 방식(`{{var}}`)의 템플릿 처리 구조 |

---

## 실행 방법

1. Kafka 실행 (로컬 환경 또는 Docker)
2. `application.yml`에 Amazon SES SMTP 계정 정보 설정
3. 애플리케이션 실행:
   ```bash
   mvn spring-boot:run


## API
```
POST /api/v1/notify
Content-Type: application/json

{
  "type": "email",
  "to": "user@example.com",
  "subject": "주문 알림",
  "template": "order",
  "variables": {
    "user": "신가을",
    "orderId": "A1234",
    "item": "Keyboard",
    "qty": 2
  }
}
```
```
POST /api/v1/notify
Content-Type: application/json

{
  "type": "email",
  "to": "user@example.com",
  "subject": "주문 알림",
  "template": "order",
  "variables": {
    "user": "신가을",
    "orderId": "A1234",
    "item": "Keyboard",
    "qty": 2
  }
}
```
