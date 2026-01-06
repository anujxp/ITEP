package com.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SettlespotEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlespotEurekaServerApplication.class, args);
	}

}
