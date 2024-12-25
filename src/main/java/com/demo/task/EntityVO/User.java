package com.demo.task.EntityVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Actual Entity

@Entity
@Table(name="Users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	//@JsonProperty("userId")
	private int userId;
	
	@NotBlank(message="Name should not be empty")
	@Column(name="name")
	//@JsonProperty("name")
	private String name;
	
	
	
	@Column(name="email")
	@Email(message="Email is invalid")
	@NotBlank(message="Email is invalid")
	//@JsonProperty("email")
	private String email;
	
	
	@Size(min=8,max=12,message="Password should be between 8 to 12 characters")
	@Column(name="password")
	//@JsonProperty("password")
	private String password;
	

	
	public User() {
		
	}
	public User(int userId, String name, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
	
	

}
