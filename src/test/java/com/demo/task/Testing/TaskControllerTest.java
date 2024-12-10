package com.demo.task.Testing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.task.Controller.taskController;
import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.service.taskServiceImpl;



//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@WebMvcTest(taskController.class)  
public class TaskControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private taskServiceImpl taskService;
	
	@InjectMocks
	private taskController taskControllers;

	private UserDto userdto;
	
	private User user;
	
	@BeforeEach
    public void setup() {
//		
		
//        MockitoAnnotations.openMocks(this);  // Initialize mock annotations
//        mockMvc = MockMvcBuilders.standaloneSetup(taskControllers).build();  // Manually set up MockMvc
    }
	

	
	@Test
	public void testCreateUser_success() throws Exception {
	    // Given
	    User user1 = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");
	    UserDto userDto1 = new UserDto(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");

	    
	    when(taskService.createUser(userDto1)).thenReturn(user1);
		//String check_user="{ \"name\": \"lavanya13\", \"email\": \"lavanya13@gmail.com\", \"password\": \"lavanya13\"}";
	    
	    
	    mockMvc.perform(post("/mytask/createUser")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{ \"name\": \"lavanya13\", \"email\": \"lavanya13@gmail.com\", \"password\": \"lavanya13\"}"))
	            .andDo(print())  
	            .andExpect(status().isCreated());  // Assert that status is 201
	            //.andExpect(jsonPath("$.email").value("lavanya13@gmail.com"));  
//	            
	}

	@Test
	public void testCreateUserFailure_ByEmail() throws Exception{
		
		User user1 = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");
	    UserDto userDto1 = new UserDto(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");

		when(taskService.createUser(userDto1)).thenReturn(user1);

		String invalidUser="{ \"name\": \"lavanya13\", \"email\": \"lavanya13\", \"password\": \"lavanya13\"}";
		mockMvc.perform(post("/mytask/createUser")
		.contentType(MediaType.APPLICATION_JSON)
		.content(invalidUser))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.email").value("Email is invalid"));
		
		
	}
	
	@Test
	public void testGetUserById_success() throws Exception{
		User user = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");
		when(taskService.getByUserId(user.getUserId())).thenReturn(Optional.of(user));
		String UserBy_id="{ \"name\": \"lavanya13\", \"email\": \"lavanya13@gmail.com\", \"password\": \"lavanya13\"}";

		mockMvc.perform(get("/mytask/retriveUser/1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(UserBy_id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.userId").value(1))
		.andExpect(jsonPath("$.name").value("lavanya13"))
		.andExpect(jsonPath("$.email").value("lavanya13@gmail.com"));
	}
	
	
	 @Test
	    void testGetByUserId_notFound() throws Exception {
	        when(taskService.getByUserId(1)).thenReturn(Optional.empty());  // Mock service to return an empty Optional

	        mockMvc.perform(get("/mytask/retriveUser/1"))
	                .andExpect(status().isNotFound());  // Expect status NOT_FOUND
	    }
	@Test
	 void testGetAllUsers_success() throws Exception {
	        
	        User user = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");

	        
	        when(taskService.getAllUsers()).thenReturn(List.of(user));

	       
	        mockMvc.perform(get("/mytask/getAllUsers"))
	                .andExpect(status().isOk())  
	                .andExpect(jsonPath("$[0].userId").value(1)) 
	                .andExpect(jsonPath("$[0].name").value("lavanya13"))  
	                .andExpect(jsonPath("$[0].email").value("lavanya13@gmail.com"))  
	                .andExpect(jsonPath("$[0].password").value("lavanya13"));  
	    }

	
	@Test
    void testGetAllUsers_empty() throws Exception {
        when(taskService.getAllUsers()).thenReturn(List.of());  

        mockMvc.perform(get("/mytask/getAllUsers"))
                .andExpect(status().isOk())  // Expect status OK
                .andExpect(content().string(""));  
    }
	
	
	 @Test
	    public void testHealthCheckInvalidInput() throws Exception {
	        String invalidInput = "abc";  // Non-numeric input

	        mockMvc.perform(MockMvcRequestBuilders.get("/mytask/healthCheck/{value}", invalidInput))
	                .andDo(print())
	                .andExpect(status().isInternalServerError())
	                .andExpect(content().string("Failed health check due to exception"));
	    }

	    @Test
	    public void testHealthCheckUserFound() throws Exception {
	        // Create a mock user
	        User user = new User();
	        user.setUserId(1);
	        user.setName("lavanya13");

	        // Mock the service to return a user with ID 1
	        when(taskService.getByUserId(1)).thenReturn(Optional.of(user));

	        String validInput = "1";  // Valid user ID

	        // Perform the GET request to the health endpoint
	        mockMvc.perform(MockMvcRequestBuilders.get("/mytask/healthCheck/{value}", validInput))
	                .andDo(print())  // Print response for debugging
	                .andExpect(status().isOk())  // Expect 200 OK for valid user
	                .andExpect(content().string("Success health check"));  // Expected success message
	    }
 


}
