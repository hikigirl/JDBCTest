## JDBC 수업

### 새 프로젝트 만들기

**New Java Project**
1. Project name: "JDBCTest"
2. JRE -> jdk-17.0.15.6-hotspot
3. Module -> 체크해제


### JDBC Driver 설치
**Oracle DB 설치시 같이 배포한 드라이버를 프로젝트에 추가**
- C:\app\product\dbhomeXE\jdbc\lib → ojdbc8.jar
- configure build path → external jars → 경로 찾아서 jar 추가


### DBUtil.java
- DB 접속, 접속 해제 메서드 담은 클래스