package com.info.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.dto.UserRequestDto;
import com.info.dto.UserResponseDto;
import com.info.entity.User;
import com.info.exception.UserNotFoundException;
import com.info.repo.UserRepo;

@Service
public class UserService {

    private final CustomUserDetailService customUserDetailService;
	 private UserRepo repo;
	 private PasswordEncoder encoder;
	 public UserService(UserRepo repo, PasswordEncoder encoder, CustomUserDetailService customUserDetailService) {
		 this.repo = repo;
		 this.encoder = encoder;
		 this.customUserDetailService = customUserDetailService;
	 }
	 
	 @Transactional
	 public UserResponseDto registerUser(UserRequestDto dto) {
		 String encodedPassword = encoder.encode(dto.getPassword());
		 User user = new User();
		 user.setEmail(dto.getEmail());
		 user.setName(dto.getName());
		 user.setPassword(encodedPassword);
		 
		 user = repo.save(user);
		 
		 UserResponseDto resDto = new UserResponseDto();
		 resDto.setId(user.getId());
		 resDto.setEmail(user.getEmail());
		 resDto.setName(user.getName());
		 
		 return resDto;
	 }
	 
	 
	 public UserResponseDto getUserById(int id) {
			User dbUser = repo.findById(id).orElseThrow(()-> new UserNotFoundException("user with id "+ id + " not found"));
			
			UserResponseDto dto = new UserResponseDto();
			dto.setId(dbUser.getId());
			dto.setEmail(dbUser.getEmail());
			dto.setName(dbUser.getName());
			return dto;
		}

	 public List<UserResponseDto> getAllUsers() {
		 List<User> userList = repo.findAll();
		 
		 List<UserResponseDto> dtoList = new ArrayList<>();
		 for(User user : userList) {
			 UserResponseDto dto = new UserResponseDto();
			 dto.setEmail(user.getEmail());
			 dto.setId(user.getId());
			 dto.setName(user.getName());
			 
			 dtoList.add(dto);
		 }
		return dtoList;
	 }
}
