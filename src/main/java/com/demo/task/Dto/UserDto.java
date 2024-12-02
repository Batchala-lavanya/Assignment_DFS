package com.demo.task.Dto;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor

public class UserDto {
	//@NotNull
	private int userId;
	
//	@NotNull(message="Name cannot be null")
//	@Size(min=8,max=32,message="Name must be between 8 to 32 charactes")
	private String name;
	
	private String email;
	
	private String password;
	
	public UserDto() {
		
	}
	

	public UserDto(int userId,String name) {
		super();
		this.userId = userId;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email=email2;
		
	}


	public void setPassword(String password2) {
		// TODO Auto-generated method stub
		this.password=password2;
	}


	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}


	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	
	

}
