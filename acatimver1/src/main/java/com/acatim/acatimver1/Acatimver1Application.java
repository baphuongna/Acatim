package com.acatim.acatimver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.acatim.acatimver1.model")
@SpringBootApplication
public class Acatimver1Application {

	public static void main(String[] args) {
		SpringApplication.run(Acatimver1Application.class, args);
	}
}