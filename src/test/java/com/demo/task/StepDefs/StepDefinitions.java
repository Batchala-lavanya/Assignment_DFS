package com.demo.task.StepDefs;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


//
public class StepDefinitions{
	
	public static final Logger logger=LoggerFactory.getLogger(StepDefinitions.class);
	 @Autowired
	    private RestTemplate restTemplate;

	    private String baseUrl = "http://localhost:8088/mytask"; // Replace with your actual base URL
	    private ResponseEntity<?> response;
	   
	    
	    private UserDto userdto;
	    private User user;
	   // private User user1;
	
	@Given("I have the following user details:")
	public void i_have_the_following_user_details(io.cucumber.datatable.DataTable dataTable) {
		logger.info("in creating method");
		String name = dataTable.cell(1, 0);
        String email = dataTable.cell(1, 1);
        String password = dataTable.cell(1, 2);

        userdto = new UserDto();
        userdto.setName(name);
        userdto.setEmail(email);
        userdto.setPassword(password);
    //throw new io.cucumber.java.PendingException();
	}
	
	
	@When("I call the createUser method")
	public void i_call_the_create_user_method() {
    // Write code here that turns the phrase above into concrete actions
		
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<UserDto> entity = new HttpEntity<>(userdto, headers);
            // Send a POST request to create the user
	        response = restTemplate.exchange(baseUrl + "/createUser", HttpMethod.POST, entity, User.class);                    // Handle exceptions
	        }
		catch(Exception e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   // throw new io.cucumber.java.PendingException();
	}
	
	
	@Then("I should create a user with details {string} {string} {string}")
	public void i_should_create_a_user_with_details(String expectedName, String expectedEmail, String expectedPassword) {
    // Write code here that turns the phrase above into concrete actions
		if (response.getStatusCode() == HttpStatus.CREATED) {
            
            User createdUser = (User) response.getBody();
            assertEquals(expectedName, createdUser.getName());
            assertEquals(expectedEmail, createdUser.getEmail());
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            // Verify the user already exists or any other error
            String errorMessage = (String) response.getBody();
           
            System.out.println("Error message: " + errorMessage);
            assertTrue(errorMessage.contains("User is already registered"));
        }
		
    //throw new io.cucumber.java.PendingException();
	}
	

	@Given("I have user details:")
	public void i_have_user_details(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
		
		logger.info("in get userBy Id method");

		//throw new io.cucumber.java.PendingException();
	}
	
	
	@When("I call getUserById method {int}")
	public void i_call_get_user_by_id_method(Integer userId) {
	    // Write code here that turns the phrase above into concrete actions
		try {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        // Create HttpEntity without body, just with headers, since it's a GET request
	        HttpEntity<?> entity = new HttpEntity<>(headers);
	        
	       
	        response = restTemplate.exchange(baseUrl + "/retriveUser/" + userId, HttpMethod.GET, entity, User.class);
	    } catch (Exception e) {
	        // Handle exceptions and return a bad request response with the error message
	        response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
//		
	    //throw new io.cucumber.java.PendingException();
	}



	@Then("I should get user details {string} {string} {string}")
	public void i_should_get_user_details(String expectedName, String expectedEmail, String expectedPassword) {
	    // Write code here that turns the phrase above into concrete actions
		
		if (response.getStatusCode() == HttpStatus.FOUND) {
            
            User createdUser = (User) response.getBody();
            assertEquals(expectedName, createdUser.getName());
            assertEquals(expectedEmail, createdUser.getEmail());
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            
            String errorMessage = (String) response.getBody();
           
            System.out.println("Error message: " + errorMessage);
            assertTrue(errorMessage.contains("User is NOT FOUND"));
       }
		
	    //throw new io.cucumber.java.PendingException();
       
	}


	
	@Given("user details:")
	public void user_details(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
		logger.info("in gett All users");
	   // throw new io.cucumber.java.PendingException();
	}
	
	@When("I call getAllUsers method")
	public void i_call_get_all_users_method() {
	    // Write code here that turns the phrase above into concrete actions
		try {
			HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<List<User>> entity = new HttpEntity<>(headers);
            // Send a POST request to create the user
	        response = restTemplate.exchange(baseUrl + "/getAllUsers", HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {});                   
	   }
		catch(Exception e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
	    //throw new io.cucumber.java.PendingException();
	}
	
	@Then("I should get All users details {string} {string} {string}")
	public void i_should_get_all_users_details(String expectedName, String expectedEmail, String expectedPassword) {
	    // Write code here that turns the phrase above into concrete actions
		boolean userFound = false;
		if (response.getStatusCode() == HttpStatus.OK) {
            
			List<User> users = (List<User>) response.getBody();
            for(User createdUser:users) {
            	if (createdUser.getName().equals(expectedName) &&
                        createdUser.getEmail().equals(expectedEmail) &&
                        createdUser.getPassword().equals(expectedPassword)) {
                        userFound = true;
                        break;
                    }
            }
        } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            
            String errorMessage = (String) response.getBody();
           
            System.out.println("Error message: " + errorMessage);
            assertTrue(errorMessage.contains("Users List is empty"));
       }
	    //throw new io.cucumber.java.PendingException();
	}

	
		

	

		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

