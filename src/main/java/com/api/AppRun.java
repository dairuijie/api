package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.api" })
public class AppRun {
	public static void main(String[] args) {
		SpringApplication.run(AppRun.class, args);
	}
}
