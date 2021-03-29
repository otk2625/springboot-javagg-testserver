package com.cos.javagg.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.user.User;
import com.cos.javagg.service.BoardService;
import com.cos.javagg.service.UserService;
import com.cos.javagg.web.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
	
	private final BoardService boardService;
	private final UserService userService;
	
	@GetMapping("/board")
	public CMRespDto<?> findAll(){
		
		return new CMRespDto<>(1, boardService.게시물전체찾기());
	}
	
	@PostMapping("/board/{id}")
	public CMRespDto<?> save(@PathVariable int id, @RequestBody BoardDto post){
		
		User user = userService.한놈찾기(id);
		
		if(user == null) {
			return new CMRespDto<>(-2, "유저가 존재하지 않음");
		}else {
			int result = boardService.게시물저장(user, post.toEntity());
			if(result == 1) {
				return new CMRespDto<>(1, "성공");
			}else {
				return new CMRespDto<>(result, "저장실패"); // -1
			}
			
			
		}
		
		
		
		
	}
}