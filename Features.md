# Global Settings
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