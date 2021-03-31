package com.cos.javagg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.BoardRepository;
import com.cos.javagg.web.dto.BoardDto;
import com.cos.javagg.web.dto.BoardUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository postRepository;

	@Transactional(readOnly = true)
	public List<Board> 게시물전체찾기() {

		return postRepository.findAll();
	}
	
	@Transactional
	public int 게시물저장(User user, Board post) {

		post.setUser(user);

		Board result = postRepository.save(post);
		if (result != null) {
			return 1;
		}

		return -1;

	}

	@Transactional
	public void 삭제하기(int id) {
		postRepository.deleteById(id);
		
	}
	
	@Transactional
	public Board 수정하기(int id, BoardUpdateDto boardUpdateDto) {
		Board board = postRepository.findById(id).get();
		
		//더티체킹
		board.setTitle(boardUpdateDto.getTitle());
		board.setContent(boardUpdateDto.getContent());
		
		return board;
	}

	@Transactional
	public Board 조회수(int id) {
		
		Board board = postRepository.findById(id).get();
		
		int result = postRepository.readCount(id);
		
		return board;
	}

	@Transactional(readOnly = true)
	public Board 게시물한건(int boardId) {
		
		return postRepository.findById(boardId).get();
	}
}
