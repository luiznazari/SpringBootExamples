# Spring boot - oAuth2 Server Application with Spring Security

### Description

Sample Spring Boot application for running oAuth2 servers. Provides autorization methods using Spring Security.

By defaut, it runs on port `8080`.

### Paths

#### `/`

Main page.

#### `/login`

A simple login form page.

The credentials are saved in memory. You can use `user/password` for a simple 'USER' or `admin/password` for a 'USER' and 'ADMIN' user.

#### `/user` (protected)

Displays a JSON object with the user's name.


#### `/userDetails` (protected)

Displays a more detailed JSON object with the user's data.

---

This project is a modified version based on [Spring Boot and OAuth2 Tutorial][1] and [Securing a Web Application][2].

[1]: https://spring.io/guides/tutorials/spring-boot-oauth2
[2]: https://spring.io/guides/gs/securing-web/