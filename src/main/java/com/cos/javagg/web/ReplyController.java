package com.cos.javagg.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.reply.Reply;
import com.cos.javagg.model.user.User;
import com.cos.javagg.service.BoardService;
import com.cos.javagg.service.ReplyService;
import com.cos.javagg.web.dto.BoardDto;
import com.cos.javagg.web.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {
	
	private final ReplyService replyService;
	private final BoardService boardService;

	@PostMapping("/reply")
	public CMRespDto<?> save(@RequestBody ReplyDto replyDto) {

		System.out.println("저장됨?");
		
		Reply reply = replyService.댓글저장(replyDto);
		
		Board board = boardService.게시물한건(replyDto.getBoardId());
		
		return new CMRespDto<>(1, board);
	}
	
	@DeleteMapping("/reply/{id}")
	public CMRespDto<?> delete(@PathVariable int id) {

		replyService.댓글삭제(id);
		
		return new CMRespDto<>(1, "성공");
	}
}
