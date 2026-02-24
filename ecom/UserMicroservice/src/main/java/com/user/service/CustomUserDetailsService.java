package com.user.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.repo.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepo;

	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dbUser = userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("Username not found"));

		GrantedAuthority authority = new SimpleGrantedAuthority("user");

		return new org.springframework.security.core.userdetails.User(dbUser.getEmail(), dbUser.getPassword(),
				List.of(authority));

	}
}