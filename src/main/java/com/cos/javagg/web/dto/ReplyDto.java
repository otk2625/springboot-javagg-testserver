package com.cos.javagg.web.dto;

import com.cos.javagg.model.reply.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDto {
    private int userId;
    private int boardId;
    private String content;

}