package com.info.settlespot.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SettlespotBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlespotBookingServiceApplication.class, args);
	}

}
