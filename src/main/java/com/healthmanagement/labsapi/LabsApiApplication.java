package com.healthmanagement.labsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Labs API", version = "1.0", description = "Labs api"))
public class LabsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsApiApplication.class, args);
	}

}
