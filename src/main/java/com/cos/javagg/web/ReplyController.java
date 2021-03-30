package com.cos.javagg.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.model.user.User;
import com.cos.javagg.service.ReplyService;
import com.cos.javagg.web.dto.BoardDto;
import com.cos.javagg.web.dto.ReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {
	
	private final ReplyService replyService;

	@PostMapping("/reply")
	public CMRespDto<?> save(@RequestBody ReplyDto replyDto) {

		System.out.println("저장됨?");
		
		replyService.댓글저장(replyDto);
		
		return new CMRespDto<>(1, "성공");
	}
}
