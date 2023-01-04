package com.paulsofts.blogapplicationservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplicationServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationServicesApplication.class, args);
	}
	
	//ModelMapper Bean Declaration
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
