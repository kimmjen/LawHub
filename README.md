1. 프로젝트 목표

법률 사건 관리 및 고객 포털 시스템은 변호사와 고객이 사건 진행 상황을 관리하고, 서로 소통할 수 있는 법률 관리 도구입니다. 사건 등록, 문서 업로드, 사건 진행 상태 확인, 변호사와의 메시징 기능을 제공합니다.

2. 주요 기능

2.1. 사건 관리 시스템 (CMS)

	•	사건 등록 및 조회
	•	사건 상태 변경 (진행 중, 완료, 취소 등)
	•	사건 관련 문서 관리 (업로드/다운로드)
	•	사건 담당 변호사 및 팀 배정
	•	사건 관련 일정 관리 및 알림 시스템

2.2. 고객 포털

	•	고객이 자신의 사건 진행 상황을 실시간으로 확인
	•	사건 관련 문서 확인 및 업로드
	•	변호사와의 메시지 기능 제공
	•	결제 내역 및 송무 비용 확인

2.3. 보안 기능

	•	OAuth 2.0 및 JWT 기반 인증 시스템
	•	사용자 권한 관리 (관리자, 변호사, 고객)
	•	HTTPS, SSL 적용을 통한 데이터 전송 보호

2.4. 추가 기능

	•	실시간 알림 (WebSocket 또는 Push 알림)
	•	일정 및 사건 히스토리 관리
	•	사건 검색 및 필터링 기능 (키워드, 날짜, 담당 변호사 등)

3. 기술 스택

3.1. 백엔드

	•	Spring Boot: 서버사이드 애플리케이션 개발
	•	PostgreSQL: 관계형 데이터베이스 관리 (사건, 사용자 정보 저장)
	•	Spring Security + JWT: 인증 및 권한 관리
	•	OAuth 2.0: 외부 인증(예: Google 로그인) 지원
	•	WebSocket: 실시간 메시징 및 알림 구현

3.2. 프론트엔드

	•	Vue.js: 사용자 인터페이스 구성
	•	Vuetify 또는 Bootstrap: 사용자 인터페이스 디자인을 위한 UI 라이브러리
	•	Axios: 백엔드 API와의 통신

3.3. 배포 및 CI/CD

	•	Docker: 컨테이너를 활용한 애플리케이션 배포
	•	GitHub Actions: CI/CD 파이프라인 구축
	•	AWS 또는 Heroku: 클라우드 서버에 배포

4. 데이터베이스 설계

4.1. 테이블 구조

	•	User (사용자): 고객 및 변호사 정보를 저장
	•	id (PK), username, password, role (admin/lawyer/client)
	•	Case (사건): 사건의 기본 정보를 저장
	•	id (PK), title, description, status (진행 중, 완료, 취소), created_at, updated_at
	•	Document (문서): 사건에 업로드된 문서 정보
	•	id (PK), case_id (FK), file_path, uploaded_at
	•	Message (메시지): 고객과 변호사 간 메시지 기록
	•	id (PK), sender_id (FK), receiver_id (FK), case_id (FK), content, sent_at
	•	Payment (결제 내역): 사건별 송무 비용 관리
	•	id (PK), case_id (FK), amount, status (지불 완료, 미지불)

5. 프로젝트 단계별 개발 계획

5.1. 1단계: 기본 백엔드 시스템 구축

	•	프로젝트 설정 (Spring Boot + PostgreSQL)
	•	기본 데이터베이스 설계 및 ORM 매핑 (JPA)
	•	사용자 인증 및 권한 관리 (JWT + OAuth 2.0)
	•	사건 관리 및 문서 업로드 API 개발
	•	기본적인 사건 조회 및 등록 기능 구현

5.2. 2단계: 프론트엔드 개발

	•	Vue.js를 활용해 사건 목록, 사건 상세 페이지, 문서 업로드 페이지 구현
	•	로그인 및 회원가입 페이지 구축 (OAuth 연동)
	•	Axios를 통해 백엔드와 통신

5.3. 3단계: 실시간 기능 추가

	•	WebSocket을 이용한 실시간 메시징 구현
