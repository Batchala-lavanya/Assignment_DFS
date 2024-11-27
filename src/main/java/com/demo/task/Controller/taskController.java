package com.demo.task.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.task.Dto.UserDto;
import com.demo.task.Entity.User;
import com.demo.task.service.taskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/mytask")
public class taskController {
	
	@Autowired
	public taskService taskSer;
	
	@PostMapping("/createUser")
	public UserDto createUser(@RequestBody User user){
		UserDto created_user=taskSer.createUser(user);
		return created_user;
		
	}
	
	@GetMapping("/retriveUser/{id}")
	public Optional<UserDto> getByUserId(@PathVariable("id") Integer id) {
		Optional<UserDto> retrived_user= taskSer.getByUserId(id);
		if(retrived_user.isEmpty()) {
			return null;
		}
		return retrived_user;
	}
	
	@GetMapping("/getAllUsers")
	public List<UserDto> AllUsers(){
		List<UserDto> all_users=taskSer.getAllUsers();
		if(all_users.isEmpty()) {
			return null;
		}
		return all_users;
	}
	

	

}
