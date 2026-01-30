package com.info.service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.info.entity.User;
import com.info.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private UserRepo repo;
	public CustomUserDetailService(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dbUser = repo.findUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

		GrantedAuthority authority = new SimpleGrantedAuthority("User");

		return new org.springframework.security.core.userdetails.User(dbUser.getEmail(), dbUser.getPassword(), List.of(authority));
	}

}
