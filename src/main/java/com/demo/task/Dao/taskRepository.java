package com.demo.task.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.task.EntityVO.User;

@Repository

public interface taskRepository extends JpaRepository<User,Integer>{

	boolean existsByName(String name);

	public User findByEmail(String email);
	
	
}
