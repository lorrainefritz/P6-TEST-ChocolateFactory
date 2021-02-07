package com.chocolateFactory.Chocolate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ChocolateApplication {
	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(ChocolateApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(ChocolateApplication.class, args);
	}

}
