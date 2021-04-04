package com.cos.javagg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.like.Likes;
import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.BoardRepository;
import com.cos.javagg.repository.LikesRepository;
import com.cos.javagg.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {
	
	private final LikesRepository likesRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	
	@Transactional
	public void 좋아요(int boardId, int userId) {
		
//		User user = userRepository.findById(userId).get();
//		
//		Board board = boardRepository.findById(boardId).get();
//		
//		Likes likes = Likes.builder()
//				.user(user)
//				.board(board)
//				.build();
//		
//		likesRepository.save(likes);
		
		
		
		likesRepository.mLike(boardId, userId);
		
	}

	@Transactional
	public void 싫어요(int likesId) {
		
		likesRepository.mdelete(likesId);
		
	}
	
	@Transactional(readOnly = true)
	public int likes찾기(int boardId, int userId) {
		return likesRepository.findLikesId(boardId,userId);
	}
}
