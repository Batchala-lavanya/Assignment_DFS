package com.demo.task.service;

import java.util.List;
import java.util.Optional;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;

public interface taskService {
	
	
	public boolean authenticate(String email,String password);
	public User createUser(UserDto userdto);
	
	public Optional<User> getByUserId(int user);
	
	public List<User> getAllUsers();
	

}
