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
import com.cos.javagg.web.dto.LoginDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	private final UserService userService;
	
	
	@PostMapping("/login")
	public CMRespDto<?> getLogin(@RequestBody LoginDto loginDto){
		
		System.out.println(loginDto.getUsername());
		System.out.println(loginDto.getPassword());
		
		User userEntity = userService.login(loginDto);
        if(userEntity != null) { //로그인 성공시
           // session.setAttribute("login", userEntity);
            return new CMRespDto<>(1, userEntity);
        }else {//로그인 실패시
           //session.invalidate(); //로그인 성공시에만 세션 아이디가 안드로이드로 넘어감.
            return new CMRespDto<>(1, null);
        }

        //return new CMRespDto<>(1, "성공");
	}
}
