package com.demo.task.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.task.Bo.UserBo;
import com.demo.task.Dao.taskRepository;
import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;
import com.demo.task.mapper.UserMapper;

@Service
public class taskServiceImpl implements taskService{
	
	
	@Autowired
	public UserBo userBo;
	
	@Autowired
	public UserMapper userMapper;
	
	
	@Override
	public UserDto createUser(User user) {
		// TODO Auto-generated method stub
		User created_user=userBo.createUser(user);
		return userMapper.toUserDto(created_user);
			
	}

	
	public List<UserDto> getAllUsers(){
		List<User> user_list=userBo.getAllUsers();
		return user_list.stream().map(userMapper::toUserDto).collect(Collectors.toList());
		
	}
	
	
	@Override
	public Optional<UserDto> getByUserId(int userId) {
		// TODO-generated method stub
		
		Optional<User> retrived_user=userBo.getByUserId(userId);
		return Optional.of(retrived_user.map(userMapper::toUserDto).orElse(null));
	}

}
