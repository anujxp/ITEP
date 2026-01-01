package com.info.fiegnclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FiegnclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FiegnclientApplication.class, args);
	}

}
