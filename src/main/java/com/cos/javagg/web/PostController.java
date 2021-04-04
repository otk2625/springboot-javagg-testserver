package com.cos.javagg.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.javagg.dto.CMRespDto;
import com.cos.javagg.model.board.Board;
import com.cos.javagg.model.user.User;
import com.cos.javagg.service.BoardService;
import com.cos.javagg.service.LikesService;
import com.cos.javagg.service.UserService;
import com.cos.javagg.web.dto.BoardDto;
import com.cos.javagg.web.dto.BoardUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {

	private final BoardService boardService;
	private final UserService userService;
	private final LikesService likesService;
	
	

	@GetMapping("/board/{page}")
	public CMRespDto<?> findAll(@PathVariable int page) {
		if(boardService.게시물전체찾기(page) == null) {
			System.out.println("게시물 없음");
			return new CMRespDto<>(-1 , null );
		}else {
			List<Board> boards = boardService.게시물전체찾기(page);
			
			return new CMRespDto<>(1,boards);
		}
		
	}

	@PostMapping("/board/{id}")
	public CMRespDto<?> save(@PathVariable int id, @RequestBody BoardDto post) {

		User user = userService.한놈찾기(id);

		if (user == null) {
			return new CMRespDto<>(-2, "유저가 존재하지 않음");
		} else {
			int result = boardService.게시물저장(user, post.toEntity());
			if (result == 1) {
				return new CMRespDto<>(1, "성공");
			} else {
				return new CMRespDto<>(result, "저장실패"); // -1
			}

		}

	}
	
	@PutMapping("/board/{id}")
	public CMRespDto<?> update(@PathVariable int id, @RequestBody BoardUpdateDto boardUpdateDto) {

		System.out.println("수정됨??");
		Board board = boardService.수정하기(id, boardUpdateDto);

		
		return new CMRespDto<>(1, board);
	}
	
	
	@PutMapping("/board/count/{id}")
	public CMRespDto<?> count(@PathVariable int id) {

		System.out.println("실행됨");
		Board board = boardService.조회수(id);

		
		return new CMRespDto<>(1, board);
	}
	
	@DeleteMapping("/board/{id}")
	public CMRespDto<?> delete(@PathVariable int id) {

		
		boardService.삭제하기(id);
		
		return new CMRespDto<>(1, "성공");
	}
	
	@PostMapping("/likes/{boardId}")
	public CMRespDto<?> like(@PathVariable int boardId, @RequestBody int userId) {
		
		System.out.println("좋아요 됨");
		
		likesService.좋아요(boardId, userId);		
		int likeId = likesService.likes찾기(boardId, userId);
		return new CMRespDto<>(1,likeId);
	}
	
	@DeleteMapping("/likes/{likesId}")
	public  CMRespDto<?> unLike(@PathVariable int likesId) {
		
		System.out.println("좋아요 취소 됨 id는 : " + likesId);
		
		
		
		likesService.싫어요(likesId);		
		
		return new CMRespDto<>(1,"성공");
	}
	
	@PostMapping("/likesId/{boardId}")
	public  CMRespDto<?> findLikes(@PathVariable int boardId, @RequestBody int userId) {
		
		System.out.println("좋아요id 찾아");
		
		int likeId = likesService.likes찾기(boardId, userId);
		
		return new CMRespDto<>(1,likeId);
	}
	
	
}
