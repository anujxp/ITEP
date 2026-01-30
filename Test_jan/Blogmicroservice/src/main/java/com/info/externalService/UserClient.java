package com.info.externalService;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.info.dto.UserDto;

@FeignClient(name="USERMICROSERVICE")
public interface UserClient {
		@GetMapping("/user/{id}")
		UserDto getUserById(@PathVariable int id);
}
