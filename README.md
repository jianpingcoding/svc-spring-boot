# svc-spring-boot

## Spring Boot 2.2.x Requirements
* Java 8, up to Java 13. (java -version)
* Maven 3.3+ (mvn version) : brew install maven / sudo apt-get install maven.
* Gradle 5.x
* Tomcat 9.0 (Servlet 4.0), Jetty 9.4 (Servlet 3.1), Undertow 2.0 (Servlet 4.0)

## Installing the Spring Boot CLI
* brew tap pivotal/tap
* brew install springboot  (/usr/local/bin)
* spring run groovy/app.groovy

## Install Visual Studio Code extensions
* Spring Boot Extension Pack
* Spring Boot Tools
* Java Extension Pack
* Lombok Annotations Support for VS Code

## Maven
* mvn clean : remove the target folder
* mvn package : it will compile your code and also package it. 
* mvn install : it will compile and package, but it will also put the package in your local repository. 
* mvn dependency:tree : prints a tree representation of your project dependencies.
* mvn spring-boot:run : Run the springboot application.
* export MAVEN_OPTS=-Xmx1024m : Setting Memory for maven.
* -Dspring.devtools.restart.enabled=false : Disable  Developer tools

## Jar
* jar tvf target/vc-sb-api-0.0.1-SNAPSHOT.jar  : jar -tf will list the files in the jar.
* java -jar target/vc-sb-api-0.0.1-SNAPSHOT.jar : To run that application

## Local Rest URLs
* http://localhost:8080/rest/string
* http://localhost:8080/rest/hello?name=Jeck
* http://localhost:8080/rest/map

## Local actuator URLs
* curl localhost:8080/actuator/health

## Related URLs
* [Spring Initializr](https://start.spring.io/)
* [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Baeldung](https://www.baeldung.com/)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

