### 의존성
- String Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- oauth2-client


### DB
### 사용자 생성
``` MySQL
create user 'javagg'@'%' identified by 'gg1234';
GRANT ALL PRIVILEGES ON *.* TO 'javagg'@'%';
create database javagg;
use javagg;
```

### yml 설정
``` yml
server:
  port: 8090
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/javagg?serverTimezone=Asia/Seoul
    username: javagg
    password: gg1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
    properties:
     hibernate.format_sql: true

  jackson:
    serialization:
      fail-on-empty-beans: false
      
  security:
    oauth2:
      client:
        registration:
         google:
            client-id: *******************************************
            client-secret: *******************************************
            scope:
            - email
            - profile
            
         facebook:
            client-id: *******************************************
            client-secret: *******************************************
            scope:
            - email
            - public_profile
```
