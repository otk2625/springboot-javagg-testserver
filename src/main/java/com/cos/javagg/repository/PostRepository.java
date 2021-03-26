package com.cos.javagg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.javagg.model.post.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
