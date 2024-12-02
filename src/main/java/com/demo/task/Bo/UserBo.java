package com.demo.task.Bo;

import java.util.List;
import java.util.Optional;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;

public interface UserBo {
	
	public User createUser(User user);
	
	public Optional<User> getByUserId(int userId);
	
	public List<User> getAllUsers();

}
