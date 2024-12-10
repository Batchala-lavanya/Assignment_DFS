package com.demo.task.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUser(UserAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> HnadleUser(UserNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getAllErrors().forEach((error) -> {
	            String fieldName = ((FieldError) error).getField();
	            String errorMessage = error.getDefaultMessage();
	            errors.put(fieldName, errorMessage);
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	 }
//	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
//	        Map<String, String> errors = new HashMap<>();
//	        ex.getBindingResult().getFieldErrors().forEach(error -> 
//	            errors.put(error.getField(), error.getDefaultMessage()));
//	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	    }
//	 
	 
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getConstraintViolations().forEach(violation -> {
	            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
