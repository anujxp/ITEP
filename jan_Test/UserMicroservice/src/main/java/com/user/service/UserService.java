package com.user.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.UserDTO;
import com.user.dto.UserRequestDTO;
import com.user.dto.UserResponseDTO;
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
     public UserResponseDTO registerUser(UserRequestDTO dto) {
             String encodedPassword = passwordEncoder.encode(dto.getPassword());
             User user = new User();
             user.setEmail(dto.getEmail());
             user.setName(dto.getName());
             user.setPassword(encodedPassword);

             user = userRepo.save(user);

             UserResponseDTO resDto = new UserResponseDTO();
             resDto.setId(user.getId());
             resDto.setEmail(user.getEmail());
             resDto.setName(user.getName());

             return resDto;
     }

	 
	 public UserDTO getUserById(int id) {
		 		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
			    UserDTO userDto = new UserDTO();
			    userDto.setId(user.getId());
//			    userDto.setUsername(user.getUsername());
			    userDto.setEmail(user.getEmail());
			    return userDto;
	 }
	

}
