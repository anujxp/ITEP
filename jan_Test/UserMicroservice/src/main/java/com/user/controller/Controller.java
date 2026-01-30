package com.user.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDTO;
import com.user.dto.UserProfileDTO;
import com.user.dto.UserRequestDTO;
import com.user.dto.UserResponseDTO;
import com.user.entity.User;
import com.user.service.UserProfileService;
import com.user.service.UserService;
import com.user.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class Controller {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService detailsService;
	private UserProfileService service; 
	private final JwtUtil jwt;

	public Controller(UserService userService,AuthenticationManager authenticationManager,UserDetailsService detailsService,JwtUtil jwt,UserProfileService service) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
        this.detailsService = detailsService;
        this.jwt = jwt;
        this.service = service;
	}

	@PostMapping("/signup")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
	}
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody UserRequestDTO user) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());
		String token = jwt.generateToken(userDetails);
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("message", "Sign in success");
		hm.put("token", token);
		return ResponseEntity.ok(hm);
	}

	

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getTenant(@PathVariable Integer id) {
		UserDTO userDto = userService.getUserById(id);
		return ResponseEntity.ok(userDto);
	}
	
	@PostMapping("/profile")
	public ResponseEntity<String> create(@RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(service.create(dto));
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<UserProfileDTO> viewProfile(@PathVariable int id){
		return ResponseEntity.ok(service.viewProfile(id));
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(service.updateProfile(dto));
	}
}
