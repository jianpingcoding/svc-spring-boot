package org.ganjp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiSpringBootApplication.class);
		app.run(args);
	}

}
