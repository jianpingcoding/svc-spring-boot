# Annotation

## Spring MVC annotations
* @RestController: The @RestController annotation tells Spring to render the resulting string directly back to the caller.
* @RequestMapping: provides “routing” information. 

## The @EnableAutoConfiguration Annotation
* This annotation tells Spring Boot to “guess” how you want to configure Spring, based on the jar dependencies that you have added. 
* Since spring-boot-starter-web added Tomcat and Spring MVC, the auto-configuration assumes that you are developing a web application and sets up Spring accordingly.