package com.demo.task.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//@AllArgsConstructor

public class UserDto {
	//@NotNull
	private int userId;
	
	@NotBlank(message="Name cannot be null")
	@Size(min=8,max=32,message="Name must be between 8 to 32 charactes")
	private String name;
	
	@Email(message="Email is invalid")
	@NotBlank(message="Email is invalid")
	private String email;
	
	@Size(min=8,max=12,message="Password should be between 8 to 12 characters")
	@Column(name="password")
	private String password;
	
	public UserDto() {
		
	}
	

	public UserDto(int userId,String name,String email,String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email=email;
		this.password=password;
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
