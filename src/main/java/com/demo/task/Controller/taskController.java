package com.demo.task.Controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;
import com.demo.task.service.taskService;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/mytask")

public class taskController {
	
	public static final Logger logger=LoggerFactory.getLogger(taskController.class);
	
	@Autowired
	public taskService taskSer;
	
	@GetMapping("/healthCheck")
	


	public ResponseEntity<String> healthCheck() {

	    logger.info("Health check initiated");

	    try {

	        // Attempt to retrieve a user or some data from the database
	    	int id=Integer.parseInt("ABC");

	        Optional<User> user = taskSer.getByUserId(id); // Example: Check for user with ID 1

	        if (user.isPresent()) {

	            logger.info("Database health check succeeded");

	            return ResponseEntity.ok("Success health check");

	        } else {

	            logger.warn("Database health check failed: User not found");

	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed health check");

	        }

	    } catch (Exception e) {

	        logger.error("Database health check exception: {}", e.getMessage());

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed health check due to exception");

	    }

	}

 

	
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userdto){
		
		logger.info("Creating user in controller layer");
		try {
		
			User created_user=taskSer.createUser(userdto);
			return new ResponseEntity<>(created_user,HttpStatus.CREATED);
		
		
		}
		catch(UserAlreadyExistsException e){
			logger.warn("User already exists {}",e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
		
		
	}
	
	@GetMapping("/retriveUser/{id}")
	public ResponseEntity<Optional<User>> getByUserId(@PathVariable("id") Integer id) {
		logger.info("Get user by id controller layer",id);
		Optional<User> retrived_user= taskSer.getByUserId(id);
		if(!retrived_user.isPresent()) {
			logger.warn("User is not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return  ResponseEntity.ok(retrived_user);
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> AllUsers(){
		logger.info("getAll users");
		List<User> all_users=taskSer.getAllUsers();
		if(all_users.isEmpty()) {
			return null;
		}
		return ResponseEntity.ok(all_users);
	}
	

	

}
