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
         - Lombok      
        : getter,setter, 생성자를 어노테이션을 통해 자동으로 생성해주는 라이브러리
        - Spring Data JPA      
        : JPA를 통해 데이터베이스에 접근 가능
        - MySQL Driver     
        : MySQL 연동
        --------
        **여기서 부터는 주석 처리되어있음.**
        - Spring boot devtools      
        : 프로젝트에서 파일이 변경되었을 때 서버 자동 재시작
        - Spring Security    
        : 스프링 기반의 애플리케이션에서는 보안을 위한 표준
        - OAuth2 Client    
        : OAuth 인증 연동
        --------
        **추가 예정**
        - Spring Rest Docs     
        : 성공하는 Test Case를 기반으로 API 스펙을 작성을 자동화해주는 도구
- **DB**
    - MySQL (8.0.x) *aws 문제로 아직 미정
    - application.properties 파일에서 DB 설정 정보 세팅

### 진행상황
1. 현재 model은 단방향으로만 설정되어있음.
    - 개발하면서 상황에 맞춰 양방향으로 설정하기.
    - 참고 : https://bumpy-trick-740.notion.site/ManyToOne-OneToMany-92a503270c384866922774ab7be2d2ea   
2. 데이터베이스에 테이블 생성까지는 완료했지만 CRUD 테스트 해보지 않음.
