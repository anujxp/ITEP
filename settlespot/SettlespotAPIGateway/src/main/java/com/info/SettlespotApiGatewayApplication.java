package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SettlespotApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlespotApiGatewayApplication.class, args);
		System.out.println("Api gateway called ");
	}

}
