package com.cos.javagg.web;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.model.user.User;
import com.cos.javagg.service.UserService;
import com.cos.javagg.web.dto.FaceBookLoginDto;
import com.cos.javagg.web.dto.JoinDto;
import com.cos.javagg.web.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	private final UserService userService;

	@PostMapping("/join")
	public CMRespDto<?> join(@RequestBody JoinDto dto) {

		System.out.println("회원가입 : " + dto.toString());
		User user = userService.registerUser(dto);
		if(user == null) {
			return new CMRespDto<>(-1, "아이디중복");
		}

		return new CMRespDto<>(1, "성공");

	}

	@PostMapping("/login")
	public CMRespDto<?> login(@RequestBody LoginDto loginDto) {

		User userEntity = userService.로그인(loginDto);

		if (userEntity == null) {
			return new CMRespDto<>(-1, null);
		}

		return new CMRespDto<>(1, userEntity);

	}
	
	@PostMapping("/login/facebook")
	public CMRespDto<?> loginFaceBook(@RequestBody FaceBookLoginDto faceBookLoginDto) {

		String username = "Facebook_"+faceBookLoginDto.getId();
		User userEntity = userService.findByUsername(username);
		
		if(userEntity == null) {
			//회원가입 시켜야함
			User UserEntity = User.builder()
					.username(username)
					.email(faceBookLoginDto.getEmail())
					.build();
			
			User registeredUser = userService.페이스북회원가입(UserEntity);
			
			return new CMRespDto<>(1, registeredUser);
			
		}else {
			//로그인 진행
			
			return new CMRespDto<>(1, userEntity);
			
		}

	}

}
