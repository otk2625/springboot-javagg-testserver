package com.cos.javagg.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PasswordEncoder passwordEncoder;
	
    @Transactional(readOnly = true)
	public User login(LoginDto loginDto) {
		
		return userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
	}

//	public User join(JoinDto joinDto) {
//		
//		User userEntity = joinDto.toEntity();
//		
//		if(userRepository.findByUsername(joinDto.getUsername()) != null){
//			return null;
//		}else {
//			userEntity.setRole(RoleType.USER);
//			return userRepository.save(userEntity);
//		}
//	}

	@Transactional(readOnly = true)
	public User 한놈찾기(int id) {
	
		return userRepository.findById(id).get();
	}
	


	@Transactional
    public User registerUser(JoinDto dto) {
        User isUser = userRepository.findByUsername(dto.getUsername());
        
        if(isUser != null) {
        	return null;
        }
        
        // **** 해싱하는 부분 ****
        String encodePassword = passwordEncoder.encode(dto.getPassword());
        User user = User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encodePassword)
                .role(RoleType.USER)
                .build();
        return userRepository.save(user);
    }

	@Transactional(readOnly = true)
	public User 로그인(LoginDto loginDto) {
		 User user = userRepository.findByUsername(loginDto.getUsername());
		 
		 
		 if(user != null) {
		// ***** 패스워드값 확인 부분 ****
	        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
	            return null;
	        }
		 }
		return user;
	}
	
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	public User 페이스북회원가입(User userEntity) {

		UUID uuid = UUID.randomUUID();
		String encPassword =new BCryptPasswordEncoder() 
				.encode(uuid.toString());

		userEntity.setPassword(encPassword);
		
		User user = userRepository.save(userEntity);
		
		return user;
	}

}
