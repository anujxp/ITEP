package com.user.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import com.user.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final UserRepository userRepo;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwt;
	public AuthController(UserService userService,UserRepository userRepo, AuthenticationManager authenticationManager,UserDetailsService userDetailsService,JwtUtil jwt) {
		this.userService = userService;
		this.userRepo = userRepo;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwt = jwt;
	}
	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody User user){
		return ResponseEntity.status(201).body(userService.saveUser(user));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<HashMap<String, Object>> signIn(@RequestBody User user){
		User dbUser =  userRepo.findByEmail(user.getEmail()).orElseThrow(()->new ResourceNotFoundException("Email not found.."));
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		UserDetails userDetails =  userDetailsService.loadUserByUsername(user.getEmail());
	    String token =  jwt.generateToken(userDetails);
		UserDto dto = new UserDto();
		dto.setEmail(dbUser.getEmail());
		dto.setId(dbUser.getId());
		dto.setContact(dbUser.getContact());
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("token", token);
		hm.put("user", dto);
	    return ResponseEntity.ok(hm);
	}
}
