package org.ganjp.api.core.config;

import lombok.extern.slf4j.Slf4j;
import org.ganjp.api.core.model.ApplicationConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Slf4j
public class InitBeanConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig appConfig = new ApplicationConfig();
        log.info("spring.application.name: {}", applicationName);
        ApplicationConfig.setApplicationName(applicationName);

        return appConfig;
    }

}
