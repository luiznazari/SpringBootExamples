# Spring boot - oAuth2 Server/Client Application (Third party autorization)

### Description

Sample Spring Boot application for running oAuth2 servers. It has both implementations: server and client.
The **client** part consumes third party autorization (by Facebook or GitHub login) and the **server** part allows another 
application to request authorization to this server and then forwards 
to third parties autentication methods.

By defaut, it runs on port `8080`.

### Paths

#### `/`

Main page.

#### `/login`

Ask user to login with Facebook or GitHub.

#### `/user` (protected)

Displays a JSON object with the user's name (aquired from autorization).


#### `/userDetails` (protected)

Displays a more detailed JSON object with the user's data (aquired from autorization).

---

This project is a modified version based on [Spring Boot and OAuth2 Tutorial][1].

[1]: https://spring.io/guides/tutorials/spring-boot-oauth2