# Spring Boot Features

## Lazy Initialization
* spring.main.lazy-initialization=true
* @Lazy(false) : disable lazy initialization for the bean

## Customizing the Banner
* Adding a banner.txt file under resources
* Setting the spring.banner.location, spring.banner.charset, spring.banner.image.location
* ${application.version}, ${spring-boot.version}, ${application.title}

## Using the ApplicationRunner or CommandLineRunner
* The CommandLineRunner interfaces provides access to application arguments as a simple string array
* If several CommandLineRunner or ApplicationRunner beans are defined that must be called in a specific order, you can additionally implement the org.springframework.core.Ordered interface or use the org.springframework.core.annotation.Order annotation.

## Accessing Command Line Properties
* --server.port=9000
* SpringApplication.setAddCommandLineProperties(false): disable them by using SpringApplication.

## Application Property Files
1. A /config subdirectory of the current directory
2. The current directory
3. A classpath /config package
4. The classpath root
* The list is ordered by precedence
* java -jar myproject.jar --spring.config.name=myproject
* java -jar myproject.jar --spring.config.location=classpath:/default.properties,file:./config/
* spring.config.name and spring.config.location are used very early to determine which files have to be loaded. 

## Profile-specific properties
* naming convention: application-{profile}.properties
* if no profiles are explicitly activated, then properties from application-default.properties are loaded
* spring.profiles.active=dev

## Encrypting Properties
* If you’re looking for a secure way to store credentials and passwords, the Spring Cloud Vault project provides support for storing externalized configuration in HashiCorp Vault.

## Using YAML Instead of Properties
* The SpringApplication class automatically supports YAML as an alternative to properties whenever you have the SnakeYAML library on your classpath.
* If you use “Starters”, SnakeYAML is automatically provided by spring-boot-starter.

## Global Setting
* You can configure global devtools settings by adding any of the following files to the $HOME/.config/spring-boot folder : spring-boot-devtools.yml

--spring.profiles.active=dev

## Spring MVC annotations
* @RestController: The @RestController annotation tells Spring to render the resulting string directly back to the caller.
* @RequestMapping: provides “routing” information. 

## The @EnableAutoConfiguration Annotation
* This annotation tells Spring Boot to “guess” how you want to configure Spring, based on the jar dependencies that you have added. 
* Since spring-boot-starter-web added Tomcat and Spring MVC, the auto-configuration assumes that you are developing a web application and sets up Spring accordingly.


## Configuration Classes
* Spring Boot favors Java-based configuration
* You need not put all your @Configuration into a single class. The @Import annotation can be used to import additional configuration classes. 
* Alternatively, you can use @ComponentScan to automatically pick up all Spring components, including @Configuration classes.