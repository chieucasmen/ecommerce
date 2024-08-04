package com.ecommerce.web;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class WebApplication {

	@Bean
	@RequestScope
	public ModelMapper modelMapper(){ return new ModelMapper(); }

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
