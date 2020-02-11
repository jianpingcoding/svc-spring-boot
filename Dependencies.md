# spring-boot-devtools
* Developer tools are automatically disabled when running a fully packaged application. 
* Disable : set the -Dspring.devtools.restart.enabled=false system property.
* If you don’t want property defaults to be applied you can set spring.devtools.add-properties to false in your application.properties.
* developer tools will enable DEBUG logging for the web logging group. 
* spring-boot-devtools disables the caching options by default.
* Applications that use spring-boot-devtools automatically restart whenever files on the classpath change. 
* DevTools relies on the application context’s shutdown hook to close it during a restart. It does not work correctly if you have disabled the shutdown hook (SpringApplication.setRegisterShutdownHook(false)).
* Excluding Resources: spring.devtools.restart.exclude=static/**,public/**
* If you want to keep those defaults and add additional exclusions, use the spring.devtools.restart.additional-exclude property instead.
*  System.setProperty("spring.devtools.restart.enabled", "false");