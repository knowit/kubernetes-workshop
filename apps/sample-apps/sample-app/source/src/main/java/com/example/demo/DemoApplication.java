package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	private static Logger logger = LoggerFactory.getLogger(Controller.class);

	public static void main(String[] args) {
		logger.info("Starting. Version: 0.0.3");

		SpringApplication.run(DemoApplication.class, args);
	}
}
