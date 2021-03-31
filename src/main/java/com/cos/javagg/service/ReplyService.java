package com.cos.javagg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.reply.Reply;
import com.cos.javagg.model.user.User;
import com.cos.javagg.repository.BoardRepository;
import com.cos.javagg.repository.ReplyRepository;
import com.cos.javagg.repository.UserRepository;
import com.cos.javagg.web.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	
	
	@Transactional
	public Reply 댓글저장(ReplyDto replyDto) {
		
		User user = userRepository.findById(replyDto.getUserId()).get();
		Board board = boardRepository.findById(replyDto.getBoardId()).get();
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replyDto.getContent())
				.build();
		
		return replyRepository.save(reply);
		
		
	}


	@Transactional
	public void 댓글삭제(int id) {
		replyRepository.deleteById(id);
		
	}

}
