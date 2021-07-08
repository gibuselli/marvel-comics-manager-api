package com.zup.comic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ComicManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicManagerApiApplication.class, args);
	}

}
