package com.demo.task.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.service.taskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/mytask")
public class taskController {
	
	public static final Logger logger=LoggerFactory.getLogger(taskController.class);
	
	@Autowired
	public taskService taskSer;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody User user){
		
		logger.info("Creating user in controller layer");
		try {
		
			UserDto created_user=taskSer.createUser(user);
			return new ResponseEntity<>(created_user,HttpStatus.CREATED);
		
		
		}
		catch(UserAlreadyExistsException e){
			logger.warn("User already exists {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
		
		
	}
	
	@GetMapping("/retriveUser/{id}")
	public ResponseEntity<Optional<UserDto>> getByUserId(@PathVariable("id") Integer id) {
		logger.info("Get user by id controller layer",id);
		Optional<UserDto> retrived_user= taskSer.getByUserId(id);
		if(!retrived_user.isPresent()) {
			logger.warn("User is not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return  ResponseEntity.ok(retrived_user);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> AllUsers(){
		logger.info("getAll users");
		List<UserDto> all_users=taskSer.getAllUsers();
		if(all_users.isEmpty()) {
			return null;
		}
		return ResponseEntity.ok(all_users);
	}
	

	

}
