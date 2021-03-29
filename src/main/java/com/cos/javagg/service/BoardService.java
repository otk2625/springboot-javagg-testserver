package com.cos.javagg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.BoardRepository;
import com.cos.javagg.web.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository postRepository;
	
	
	public List<Board> 게시물전체찾기(){
		
		return postRepository.findAll();
	}


	public int 게시물저장(User user, Board post) {
		
		post.setUser(user);
		
		Board result = postRepository.save(post);
		if(result != null) {
			return 1;
		}
		
		return -1;
		
	}
}
