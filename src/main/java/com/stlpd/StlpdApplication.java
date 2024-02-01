package com.stlpd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StlpdApplication {

	public static void main(String[] args) {
		SpringApplication.run(StlpdApplication.class, args);
	}

}
