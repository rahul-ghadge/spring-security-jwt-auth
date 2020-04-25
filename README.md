# Spring Boot Security Jwt Authentication

This is a sample project to provide example on how to add JWT token authentication in a spring boot application.
The example uses maven as a build tool and also sample to run main method on application startup so that anybody can get started by simply running Application.java
If any user is not present in PostgreSQL DB it will 2(user and admin) users at application startup time. passwords of users are encrypted eith `BCryptEncoder`.

Users:
 1. User name: `user` and password: `password`
 2. User name: `admin` and password: `admin123`

## Technology Used

 1. Spring Boot (2.2.6.RELEASE)
 2. JWT (0.9.1)
 3. PostgreSQL
 4. JDK 1.8.0_251