package com.cos.javagg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.javagg.model.board.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
