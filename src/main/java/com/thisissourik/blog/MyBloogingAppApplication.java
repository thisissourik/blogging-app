package com.thisissourik.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBloogingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBloogingAppApplication.class, args);
		System.out.println("Spring Boot Application Running ... Port 8080 ");
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
