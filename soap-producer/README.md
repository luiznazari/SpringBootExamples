# Spring Boot - SOAP WebService Provider

### Description

Sample Spring Boot application for running SOAP WebServers.

Provides `.wsdl` files and receive SOAP requests via HTTPS.

By defaut, it runs on port `8080`.

### Paths

#### `/ws`

WebService URI.

#### `/ws/countries.wsdl`

Provides the `.wsdl` file.

### Project

#### `src/main/java/io/spring/guides/gs_producing_web_service`

Holds the classes distributed by `countries.wsdl`.

#### `src/main/resources`

Contains all keystores and truststores used by the application 

---

This project is a modified version based on [Producing a SOAP web service][1] and [SOAP over HTTPS with client certificate authentication][2].

[1]: https://spring.io/guides/gs/producing-web-service/
[2]: http://zoltanaltfatter.com/2016/04/30/soap-over-https-with-client-certificate-authentication/