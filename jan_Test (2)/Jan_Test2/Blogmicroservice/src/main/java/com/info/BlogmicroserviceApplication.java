package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlogmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogmicroserviceApplication.class, args);
	}

}
