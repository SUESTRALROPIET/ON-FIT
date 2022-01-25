# 공통-Sub PJT 2
### 초기 개발환경

- **JAVA**
    - 버전 : Open JDK 1.8.0_192
- **IDE**
    - IntelliJ
- **Spring Boot**
    - 빌드 도구 : Gradle
    - Dependency **(build.gradle 파일에서 수정)**
        - Spring Web      
        : Spring Boot기반 Web 프로젝트 개발시 필수
        --------
        **여기서 부터는 주석 처리되어있음.**
        - Spring boot devtools      
        : 프로젝트에서 파일이 변경되었을 때 서버 자동 재시작
        - Lombok      
        : getter,setter, 생성자를 어노테이션을 통해 자동으로 생성해주는 라이브러리
        - Spring Data JPA      
        : JPA를 통해 데이터베이스에 접근 가능
        - MySQL Driver     
        : MySQL 연동
        - Spring Security    
        : 스프링 기반의 애플리케이션에서는 보안을 위한 표준
        - OAuth2 Client    
        : OAuth 인증 연동
        --------
        **추가 예정**
        - Spring Rest Docs     
        : 성공하는 Test Case를 기반으로 API 스펙을 작성을 자동화해주는 도구
- **DB**
    - MySQL (5.7.35)
    - application.properties 파일에서 DB 설정 정보 세팅
