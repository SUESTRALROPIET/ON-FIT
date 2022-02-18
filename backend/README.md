## 로컬에서 테스트하는 과정
1. backend 폴더가 아닌 ssafit 폴더를 IntelliJ에서 Open
2. 자동으로 gradle build 해줌. -> 기다리기 (우측 밑에 표시됨)
3. src/main/resources/application.properties에서 DB 설정 정보(username,password) 변경
4. MySQL에서 'create database onfit' 실행해서 onfit DB 스키마 생성
5. src/main/java/SsafitApplication 실행
6. onfit DB 스키마에 테이블 생성되고 http://localhost:8081/swagger-ui.html 접속되면 백엔드 서버 구동 성공!
