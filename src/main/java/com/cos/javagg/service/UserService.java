package com.cos.javagg.service;

import org.springframework.stereotype.Service;

import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.UserRepository;
import com.cos.javagg.web.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public User login(LoginDto loginDto) {
		
		return userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
	}

}
