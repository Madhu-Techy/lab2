package com.example.courseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.courseapp.repository")
public class CourseappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseappApplication.class, args);
	}

}
