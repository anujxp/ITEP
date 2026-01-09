package com.info.settlespot.propertyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SettlespotPropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlespotPropertyServiceApplication.class, args);
	}

}
