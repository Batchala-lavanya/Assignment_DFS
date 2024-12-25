package com.demo.task.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.task.Bo.UserBo;
import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;
import com.demo.task.mapper.UserMapper;

import jakarta.transaction.Transactional;

@Service
public class taskServiceImpl implements taskService{
    private static final Logger logger = LoggerFactory.getLogger(taskServiceImpl.class);
	
	
	@Autowired
	public UserBo userBo;
	
	@Autowired
	public UserMapper userMapper;
	
	
	
	
	@Transactional
	@Override
	public User createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		//UserDto userDto=userMapper.toUserDto(user);
		User user1=userMapper.toUser(userdto);
		logger.info("Creating User");
		User created_user=userBo.createUser(user1);
		if(created_user==null) {
			throw new UserAlreadyExistsException("user is existing already");
		}
		return created_user;
		
		
		
			
	}

	
	public List<User> getAllUsers(){
		logger.info("Getting all Users");
		List<User> user_list=userBo.getAllUsers();
		if(user_list.isEmpty()) {
			logger.info("Users_list is empty");
		}
		return user_list;
	}
	
	
	@Override
	public Optional<User> getByUserId(int userId) {
		// TODO-generated method stub
		logger.info("getting user by Id",userId);
		
		Optional<User> retrived_user=userBo.getByUserId(userId);
		if(!retrived_user.isPresent()) {
			logger.info("no user with userId {}",userId);
			throw new UserNotFoundException("User is Not found");
		}
		//return Optional.of(retrived_user.map(userMapper::toUserDto).orElse(null));
		return retrived_user;
	}

	@Override
	public boolean authenticate(String email, String password) {
		// TODO Auto-generated method stub
		try {
			boolean value=userBo.authenticate(email, password);
			if(value==true) {
				return true;
			}
		
		}catch(UserNotFoundException e) {
			return false;
		}
		return false;
	}

}
