package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserProfileDTO;
import com.user.service.UserProfileService;



@RestController
@RequestMapping("/profile")
public class UserProfileController {
	private UserProfileService service;
	public UserProfileController (UserProfileService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(service.create(dto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDTO> viewProfile(@PathVariable int id){
		return ResponseEntity.ok(service.viewProfile(id));
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody UserProfileDTO dto){
		return ResponseEntity.ok(service.updateProfile(dto));
	}
	
}