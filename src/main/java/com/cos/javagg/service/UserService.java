package com.cos.javagg.service;

import org.springframework.stereotype.Service;

import com.cos.javagg.model.user.RoleType;
import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.UserRepository;
import com.cos.javagg.web.dto.JoinDto;
import com.cos.javagg.web.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	public User login(LoginDto loginDto) {
		
		return userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
	}

	public User join(JoinDto joinDto) {
		
		User userEntity = joinDto.toEntity();
		
		if(userRepository.findByUsername(joinDto.getUsername()) != null){
			return null;
		}else {
			userEntity.setRole(RoleType.USER);
			return userRepository.save(userEntity);
		}
	}

	public User 한놈찾기(int id) {
	
		return userRepository.findById(id).get();
	}

}
