package com.demo.task.Bo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.task.Dao.taskRepository;
import com.demo.task.Entity.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;
@Service
public class UserBoImpl implements UserBo{
	
	@Autowired
	public taskRepository userRepo;
	
	
	public static final Logger logger=LoggerFactory.getLogger(UserBoImpl.class);
	
	
	@Override
	public User createUser(User user) throws UserAlreadyExistsException{
		logger.info("creating user Bo_Layer");
		logger.debug("Checking if user with name {} exists", user.getName());
	    
		if(userRepo.existsByName(user.getName())) {
			throw new UserAlreadyExistsException("User is Already Present");	
		}
		User user_created=userRepo.save(user);
		logger.info("user successfull created");
		
		return user_created;
	}

	@Override
	public Optional<User> getByUserId(int userId) throws UserNotFoundException{
		// TODO Auto-generated method stub
		logger.info("getting user by userId bo_layer {}",userId);
		Optional<User> user=userRepo.findById(userId);
		if(!user.isPresent()) {
			logger.warn("no user with userId {}",userId);
			throw new UserNotFoundException("User is not found");
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		logger.info("getting all users");
		
		// TODO Auto-generated method stub
		List<User> list_users=userRepo.findAll();
		if(list_users.isEmpty()) {
			logger.warn("users_list is empty");
		}
		return list_users;
	}

}
