package com.demo.task.service;

import java.util.List;
import java.util.Optional;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;

public interface taskService {
	
	public User createUser(UserDto userdto);
	
	public Optional<User> getByUserId(int userdtoId);
	
	public List<User> getAllUsers();
	

}
