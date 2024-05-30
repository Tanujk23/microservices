package com.tanuj.question_service;

import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuestionServiceApplication {

	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
