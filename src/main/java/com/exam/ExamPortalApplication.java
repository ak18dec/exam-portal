package com.exam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.exam.datafactory.Factory.*;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Exam Portal Server....");
		System.out.println("Populating default master data....");

		createRoles().forEach(System.out::println);
		createUsers().forEach(System.out::println);
		createGenres().forEach(System.out::println);

	}
}
