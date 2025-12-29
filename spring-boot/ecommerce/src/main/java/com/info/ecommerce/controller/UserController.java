package com.info.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.ecommerce.dto.UserResponseDTO;
import com.info.ecommerce.entity.User;
import com.info.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody User user){
		User dbUser = userService.registerUser(user);
		UserResponseDTO userDto = new UserResponseDTO();
		userDto.setId(dbUser.getId());
		userDto.setEmail(dbUser.getEmail());
		userDto.setUserName(dbUser.getUserName());
		return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
	}


	@PostMapping("/login")
	public ResponseEntity<UserResponseDTO> authenticateUser(@RequestBody User user){
		User dbUser =  userService.authenticateUser(user);
		UserResponseDTO userDTO = new UserResponseDTO();
		userDTO.setId(dbUser.getId());
		userDTO.setEmail(dbUser.getEmail());
		userDTO.setUserName(dbUser.getUserName());
		return ResponseEntity.ok().body(userDTO);
	}

}
