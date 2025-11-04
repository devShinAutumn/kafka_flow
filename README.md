# ⚙️ Kafka Flow
Spring Boot와 Kafka를 이용한 비동기 메시징 시스템입니다.  
이메일 및 카카오톡 알림을 안정적으로 전송하기 위해 설계된 간단한 알림 서비스입니다.

---

## 🧩 Overview
- 대량의 알림 데이터를 안정적으로 처리하기 위한 Kafka 기반 메시징 구조  
- 이메일은 Amazon SES를 이용한 SMTP 발송 방식  
- 카카오톡은 실제 연동 대신 테스트용 시뮬레이션 로그 출력 방식  
- 향후 실 서비스 연동 시, Producer/Consumer 확장 가능하도록 구성  

---

## 🧠 Tech Stack
- **Language**: Java 21  
- **Framework**: Spring Boot 3.x  
- **Messaging**: Apache Kafka  
- **Database**: PostgreSQL  
- **Mail Service**: Amazon SES (SMTP)  
- **Build Tool**: Maven  

---

## 🚀 How to Run
1. Kafka 실행 (Docker Compose 또는 로컬 설치)
2. `application.yml`에 Amazon SES 계정 정보 입력  
3. 실행:
   ```bash
   mvn spring-boot:run


POST /api/v1/notify
Content-Type: application/json
{
  "type": "email",
  "to": "user@example.com",
  "subject": "주문 알림",
  "template": "order",
  "variables": {
    "user": "autumn",
    "orderId": "A1234",
    "item": "Keyboard",
    "qty": 2
  }
}



POST /api/v1/notify
{
  "type": "kakao",
  "to": "01012345678",
  "template": "alert",
  "variables": {
    "user": "autumn",
    "reqId": "R-88",
    "message": "배치 완료"
  }
}

---
## 📦 Repository Structure
src/
 ├── main/
 │   ├── java/com/example/kafka_notify/
 │   │    ├── controller/
 │   │    ├── service/
 │   │    ├── producer/
 │   │    └── consumer/
 │   └── resources/
 │        └── application.yml
 └── test/
      └── KafkaNotifyTests.java


---
## 🪶 Note
	•	카카오톡 발송은 실제 API 호출 대신 로그로 동작 (테스트용)
	•	Amazon SES 연동 시 계정 인증 필요
	•	향후 Redis 큐나 SQS로 확장 가능
