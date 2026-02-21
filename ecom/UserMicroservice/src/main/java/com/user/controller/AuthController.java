package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody User user){
		return ResponseEntity.status(201).body(userService.saveUser(user));
	}
}
