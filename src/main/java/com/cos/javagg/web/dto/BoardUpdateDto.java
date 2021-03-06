package com.cos.javagg.web.dto;

import javax.persistence.Lob;

import com.cos.javagg.model.board.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardUpdateDto {
	private String title;
	private String content;
	
	public Board toEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.build();
	}
}
