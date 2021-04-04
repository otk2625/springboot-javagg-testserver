package com.cos.javagg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.javagg.model.like.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer>{

	@Modifying
	@Query(value = "INSERT INTO likes(boardId, userId) VALUES(:boardId, :userId) ", nativeQuery = true)
	int mLike(int boardId, int userId);

	@Modifying
	@Query(value = "DELETE FROM likes WHERE id = :likesId ", nativeQuery = true)
	int mdelete(int likesId);
	

	@Query(value = "SELECT id FROM likes WHERE boardId = :boardId AND userId = :userId ", nativeQuery = true)
	int findLikesId(int boardId, int userId);
	
}
