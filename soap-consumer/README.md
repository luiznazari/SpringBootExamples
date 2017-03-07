# Spring Boot - SOAP WebService Consumer

### Description

Sample Spring Boot application for Consuming SOAP WebServers.

HTTPS enabled.

Automatically generate classes from the provided `.wsdl` file using [JAXB2 Maven Plugin][3].

By defaut, it runs on port `8000` and consumes server on port `8080`.

### Running

This project generate a standalone `.jar`. When executed it'll consume the WebService.

#### Arguments

*  `name` - name of the Country whose information is desired.

### Project

#### `target/generated-sources/xjc`

Output directory for **JAXB2 Maven Plugin** auto generated classes.

#### `src/main/resources`

Contains all keystores and truststores used by the application 

#### `src/main/resources/schemas`

Contains the `.wsdl` files provided by the server. It can be discarded when configuring the server's `.wsdl` URL (`/ws/countries.wsdl`) directly in the **JAXB2 Maven Plugin** on `pom.xml` (requires a running server).

---

This project is a modified version based on [Consuming a SOAP web service][1] and [SOAP over HTTPS with client certificate authentication][2].

[1]: https://spring.io/guides/gs/consuming-web-service/
[2]: http://zoltanaltfatter.com/2016/04/30/soap-over-https-with-client-certificate-authentication/
[3]: https://github.com/highsource/maven-jaxb2-plugin