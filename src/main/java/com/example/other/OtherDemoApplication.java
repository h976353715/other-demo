package com.example.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class OtherDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtherDemoApplication.class, args);
	}

}
