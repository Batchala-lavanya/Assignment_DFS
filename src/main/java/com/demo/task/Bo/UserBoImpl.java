package com.demo.task.Bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.task.Dao.taskRepository;
import com.demo.task.Entity.User;
@Service
public class UserBoImpl implements UserBo{
	
	@Autowired
	public taskRepository userRepo;
	
	
	
	@Override
	public User createUser(User user) {
		User user_created=userRepo.save(user);
		
		return user_created;
	}

	@Override
	public Optional<User> getByUserId(int userId) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findById(userId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list_users=userRepo.findAll();
		return list_users;
	}

}
