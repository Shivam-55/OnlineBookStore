package com.code.onlinebookstore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Represents a Spring Boot application for an online bookstore.
 * This class contains the main method to run the application.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class OnlinebookstoreApplication {

	/**
	 * Creates and returns a ModelMapper bean.
	 * This bean can be used for object mapping and conversion.
	 * @return A ModelMapper instance.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * The main method to start the Spring Boot application.
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(OnlinebookstoreApplication.class, args);
	}

}
