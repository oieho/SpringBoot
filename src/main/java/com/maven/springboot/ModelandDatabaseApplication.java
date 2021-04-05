package com.maven.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModelandDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModelandDatabaseApplication.class, new String[] {"100"});
	}

}