package com.healthmanagement.labsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class LabsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsApiApplication.class, args);
	}

}
