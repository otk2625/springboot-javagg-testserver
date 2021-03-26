package com.cos.javagg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.javagg.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	//@Query(value = "select * from user where username=:username, password=:password ", nativeQuery = true)
	User findByUsernameAndPassword(String username, String password);
}
