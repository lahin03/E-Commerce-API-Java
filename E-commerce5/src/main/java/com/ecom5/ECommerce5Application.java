package com.ecom5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecom5"})
public class ECommerce5Application {

	public static void main(String[] args) {
		SpringApplication.run(ECommerce5Application.class, args);
	}

}
