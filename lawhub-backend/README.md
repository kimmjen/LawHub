## 모놀리식 아키텍처 프로젝트 구조

```
lawhub-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── lawhub/
│   │   │           ├── controller/
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── CaseController.java
│   │   │           │   ├── DocumentController.java
│   │   │           │   └── MessagingController.java
│   │   │           ├── dto/
│   │   │           │   ├── UserResponse.java
│   │   │           ├── model/
│   │   │           │   ├── User.java
│   │   │           │   ├── Case.java
│   │   │           │   ├── Document.java
│   │   │           │   └── Message.java
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── CaseRepository.java
│   │   │           │   └── DocumentRepository.java
│   │   │           ├── security/
│   │   │           │   ├── JwtTokenProvider.java
│   │   │           │   └── SecurityConfig.java
│   │   │           └── service/
│   │   │               ├── AuthService.java
│   │   │               ├── CaseService.java
│   │   │               ├── DocumentService.java
│   │   │               └── MessagingService.java
│   └── test/
│       └── java/
│           └── com/
│               └── lawhub/
│                   └── LawhubBackendApplicationTests.java
├── build.gradle
└── src/main/resources/application.properties
```

## 주요 폴더 설명

### 2.1 controller/ (컨트롤러)

Controller는 클라이언트로부터 들어오는 HTTP 요청을 처리하고, 그 결과를 반환하는 역할을 합니다. 비즈니스 로직은 최대한 Service 계층에서 처리하고, 컨트롤러는 주로 요청을 받아 전달하고 응답하는 역할에 집중해야 합니다.

### 2.2 service/ (서비스)

Service는 비즈니스 로직을 처리하는 계층입니다. 데이터 처리, 검증, 비즈니스 규칙 등을 정의하고, 필요할 때 Repository 계층을 호출하여 데이터베이스와 상호작용합니다.

### 2.3 repository/ (리포지토리)

Repository는 데이터베이스와 직접 상호작용하는 계층입니다. JPA 또는 MyBatis를 사용해 데이터를 저장, 조회, 삭제, 수정하는 작업을 담당합니다. 일반적으로 인터페이스로 정의하고, Spring Data JPA의 기본 CRUD 메서드를 사용합니다.

### 2.4 model/ (모델/엔티티)

Model 또는 Entity는 데이터베이스 테이블과 매핑되는 객체입니다. 이 객체는 애플리케이션 내에서 데이터를 저장하거나 처리하는 데 사용됩니다.

### 2.5 dto/ (데이터 전송 객체)

DTO는 클라이언트와의 데이터 전송에 사용되는 객체입니다. 엔티티와 달리, 클라이언트에 노출할 필요가 없는 데이터를 제외하거나, 추가적인 검증이 필요한 데이터를 담아 처리할 수 있습니다.

### 2.6 config/ (설정)

Config 폴더는 애플리케이션의 설정 파일을 관리합니다. 예를 들어, Spring Security 설정, CORS 설정 또는 애플리케이션의 전반적인 설정을 여기에 포함시킵니다.

### 2.7 exception/ (예외 처리)

Exception 폴더는 애플리케이션 전역에서 발생할 수 있는 예외를 처리하는 클래스들을 담습니다. 커스텀 예외와 글로벌 예외 처리를 위한 클래스들이 위치합니다.

### 2.8 util/ (유틸리티)

Util 폴더에는 공통적으로 사용될 유틸리티 클래스들이 포함됩니다. 예를 들어, 날짜 형식 변환, 문자열 처리, 파일 처리 등의 공통 기능을 여기에 포함할 수 있습니다.

### 2.9 application.yml (환경 설정 파일)

Spring Boot 애플리케이션에서 전역 설정을 관리하는 파일입니다. 데이터베이스 연결 정보, 로그 설정, 서버 포트, CORS 설정 등이 포함됩니다.