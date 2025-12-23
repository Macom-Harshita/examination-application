package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ExaminationApplication {

	public static void main(String[] args) {
		ApplicationContext con = SpringApplication.run(ExaminationApplication.class, args);
	}

}
