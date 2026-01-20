package com.blog.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.dto.UserDTO;

@FeignClient(name = "USERMICROSERVICE")
public interface UserClient {
	@GetMapping("/auth/user/{id}")
   UserDTO getUserById(@PathVariable int id);
}
