package com.demo.task.StepDefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.service.taskServiceImpl;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions{
	
	@Autowired
	private taskServiceImpl taskSer;
	private UserDto userdto;
	private User user;
	private User created_user;
	
	
	@Given("I have the following user details:")
	public void i_have_the_following_user_details(io.cucumber.datatable.DataTable dataTable) {
		 List<List<String>> data = dataTable.asLists(String.class);

	        
	        for (int i = 1; i < data.size(); i++) {  // Skip the header row
	            List<String> row = data.get(i);  // Get each row of user data

	            // Assign values from the table into the userDto
	            int userId = Integer.parseInt(row.get(0));  
	            String name = row.get(1);                    
	            String email = row.get(2);                   
	            String password = row.get(3);                
	            
	            userdto = new UserDto();
	            userdto.setUserId(userId);  
	            userdto.setName(name);
	            userdto.setEmail(email);
	            userdto.setPassword(password);
	        }
	            //throw new io.cucumber.java.PendingException();
		
	          
	}
	
	
	@When("I call the createUser method")
	public void i_call_the_create_user_method() {
	    taskSer.createUser(userdto);
	    throw new io.cucumber.java.PendingException();
	}
	
	
	@Then("I should create a user with details \\{int} \\{string} \\{string} \\{string}")
	public void i_should_create_a_user_with_details(int expectedUserId,String expectedName,String expectedEmail,String expectedPassword) {
		
		assertNotNull(created_user);  // Ensure the user is not null
	       assertEquals(expectedUserId, created_user.getUserId());  // Validate userId
	       assertEquals(expectedName, created_user.getName());      // Validate name
	       assertEquals(expectedEmail, created_user.getEmail());    // Validate email
	       assertEquals(expectedPassword, created_user.getPassword());
	    throw new io.cucumber.java.PendingException();
	}
	
}

