package com.demo.task.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.task.EntityVO.User;

@Repository

public interface taskRepository extends JpaRepository<User,Integer>{

	boolean existsByName(String name);
	
	
}
