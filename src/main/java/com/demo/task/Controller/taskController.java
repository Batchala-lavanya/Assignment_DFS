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
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.ErrorResponse;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;
import com.demo.task.service.taskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/mytask")

public class taskController {
	
	public static final Logger logger=LoggerFactory.getLogger(taskController.class);
	
	@Autowired
	public taskService taskSer;
	
	
	
	public taskController(taskService taskSer) {
		super();
		this.taskSer = taskSer;
	}
	
	
	

	@GetMapping("/healthCheck/{value}")
	

	public ResponseEntity<String> healthCheck(@PathVariable("value") String value) {

	    logger.info("Health check initiated");

	    try {

	        // Attempt to retrieve a user or some data from the database
	    	int id=Integer.parseInt(value);

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
	
	@Operation(summary = "Creates a new User")
    @ApiResponse(responseCode = "201", description = "Created User")
	@ApiResponse(responseCode = "400", description ="Bad Request")
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userdto) throws UserAlreadyExistsException {
	    logger.info("Creating user in controller layer");

	    try {
	        User created_user = taskSer.createUser(userdto);
	        return new ResponseEntity<>(created_user, HttpStatus.CREATED);
	    } catch (UserAlreadyExistsException e) {
	        logger.warn("User already exists: {}", e.getMessage());
	        return new ResponseEntity<>(new ErrorResponse("User is already registered."), HttpStatus.BAD_REQUEST);
	    }
	}

	@Operation(summary = "Retrive User")
    @ApiResponse(responseCode = "200", description = "Ok User retrived")
	@GetMapping("/retriveUser/{id}")
	public ResponseEntity<?> getByUserId(@PathVariable("id") Integer id) {
	    
		logger.trace("Get user by id controller layer", id);
	    
	    // Assuming taskSer.getByUserId() fetches the user from database
	    try {
	        Optional<User> retrivedUser = taskSer.getByUserId(id);
	        
	        if (!retrivedUser.isPresent()) {
	            logger.warn("User is not found with ID: " + id);
	            // Instead of throwing an exception, just return a 'User Not Found' response.
	            return new ResponseEntity<>(new ErrorResponse("User not found."), HttpStatus.NOT_FOUND);
	        }
	        
	        return ResponseEntity.ok(retrivedUser);

	    } catch (UserNotFoundException e) {
	        // Catch any other unexpected exceptions and return a generic 500 error
	        logger.error("Error occurred while retrieving user by id: ", e);
	        return new ResponseEntity<>(new ErrorResponse("User is not found"), HttpStatus.NOT_FOUND);
	    }
	}


	@Operation(summary = "Get All  Users")
    @ApiResponse(responseCode = "200", description = "Ok Retrived All Users")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> AllUsers(){
		logger.info("getAll users ");
		List<User> all_users=taskSer.getAllUsers();
		if(all_users.isEmpty()) {
			return null;
		}
		return ResponseEntity.ok(all_users);
	}
	

	

}
