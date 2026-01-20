package com.user.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo,PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	
	}
	 @Transactional
	   public UserDTO registerUser(User user) {
	           String encryptedPassword = passwordEncoder.encode(user.getPassword());
	           user.setPassword(encryptedPassword);
	           User dbUser =  userRepo.save(user);
	           UserDTO dto = new UserDTO();
	           dto.setId(dbUser.getId());
	           dto.setEmail(dbUser.getEmail());
	           dto.setUsername(dbUser.getUsername());
	           return dto;
	   }
	 public UserDTO getUserById(int id) {
		 		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
			    UserDTO userDto = new UserDTO();
			    userDto.setId(user.getId());
			    userDto.setUsername(user.getUsername());
			    userDto.setEmail(user.getEmail());
			   
			    return userDto;
	 }
	

}
