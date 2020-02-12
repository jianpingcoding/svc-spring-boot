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

## Configuring Random Values
* my.secret=${random.value}
* my.number=${random.int}
* my.bignumber=${random.long}
* my.uuid=${random.uuid}
* my.number.less.than.ten=${random.int(10)}
* my.number.in.range=${random.int[1024,65536]}

## Admin Features
* spring.application.admin.enabled
* local.server.port

## Global Setting
* You can configure global devtools settings by adding any of the following files to the $HOME/.config/spring-boot folder : spring-boot-devtools.yml

# Annotation



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