package com.demo.task.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> handleUser(UserAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<String> HnadleUser(UserNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}
