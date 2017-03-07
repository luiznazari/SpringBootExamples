# Spring Boot - oAuth2 Client Application

### Description

Sample Spring Boot application for consuming oAuth2 servers.

Most of it's configuration is handled by Spring Boot's auto configuration based on the properties of `client.yml`.

By defaut, it runs on port `8000` and consumes server on port `8080`.

### Paths

#### `/client`

Main page. If the client is not autorized yet it'll request an autorization token to the running app server (may be by [Third party][1] or [Spring Security][2] autorization). If the access is successfully granted it'll show the `Hello <username>` message.

#### `/client/user`

Same as `/client`.


---

This project is a modified version based on [Spring Boot and OAuth2 Tutorial - Creating a Client Application](https://spring.io/guides/tutorials/spring-boot-oauth2/#_creating_a_client_application).

[1]: ../oauth2-server-client
[2]: ../oauth2-server-security