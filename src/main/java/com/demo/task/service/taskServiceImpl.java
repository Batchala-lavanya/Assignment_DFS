package com.demo.task.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.task.Bo.UserBo;
import com.demo.task.Dao.taskRepository;
import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.mapper.UserMapper;

@Service
public class taskServiceImpl implements taskService{
    private static final Logger logger = LoggerFactory.getLogger(taskServiceImpl.class);
	
	
	@Autowired
	public UserBo userBo;
	
	@Autowired
	public UserMapper userMapper;
	
	
	@Override
	public User createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		User user=userMapper.toUser(userdto);
		logger.info("Creating User");
		User created_user=userBo.createUser(user);
		return created_user;
		
		
		
			
	}

	
	public List<User> getAllUsers(){
		logger.info("Getting all Users");
		List<User> user_list=userBo.getAllUsers();
		//return user_list.stream().map(userMapper::toUser()).collect(Collectors.toList());
		return user_list;
	}
	
	
	@Override
	public Optional<User> getByUserId(int userId) {
		// TODO-generated method stub
		logger.info("getting user by Id",userId);
		
		Optional<User> retrived_user=userBo.getByUserId(userId);
		if(retrived_user==null) {
			logger.info("no user with userId {}",userId);
		}
		//return Optional.of(retrived_user.map(userMapper::toUserDto).orElse(null));
		return retrived_user;
	}

}
