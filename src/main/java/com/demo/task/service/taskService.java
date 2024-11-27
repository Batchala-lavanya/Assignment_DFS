package com.demo.task.service;

import java.util.List;
import java.util.Optional;

import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;

public interface taskService {
	
	public UserDto createUser(User user);
	
	public Optional<UserDto> getByUserId(int userId);
	
	public List<UserDto> getAllUsers();
	

}
