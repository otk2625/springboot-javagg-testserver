package com.cos.javagg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.javagg.model.board.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Modifying
	@Query(value="update board set readCount = readCount + 1 where id = :id ",nativeQuery = true)
	int readCount(int id);

	@Query(value="select * from board order by createDate desc limit :page, 10 ",nativeQuery = true)
	List<Board> findAllLimit(int page);

}
