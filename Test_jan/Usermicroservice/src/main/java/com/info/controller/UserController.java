package com.info.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.UserRequestDto;
import com.info.dto.UserResponseDto;
import com.info.service.CustomUserDetailService;
import com.info.service.UserService;
import com.info.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService service;
	private AuthenticationManager manager;
	private CustomUserDetailService detailsService;
	private JwtUtil jwt;
	
	public UserController(UserService service, AuthenticationManager manager, JwtUtil jwt,CustomUserDetailService detailsService) {
		this.service = service;
		this.manager = manager;
		this.jwt = jwt;
		this.detailsService = detailsService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto dto){
		return ResponseEntity.ok(service.registerUser(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserRequestDto user){
		manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());
		
		String token = jwt.generateToken(userDetails);
		HashMap<String, String> map = new HashMap<>();
		map.put("User Token", token);
		
		return ResponseEntity.status(HttpStatus.SC_ACCEPTED).body(map);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable int id){
		UserResponseDto dto = service.getUserById(id);
		return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(dto);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserResponseDto>> getAll(){
		List<UserResponseDto> dto = service.getAllUsers();
		return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(dto);
	}
}
