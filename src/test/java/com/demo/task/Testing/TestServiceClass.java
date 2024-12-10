package com.demo.task.Testing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.task.Bo.UserBo;
import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;
import com.demo.task.mapper.UserMapper;
import com.demo.task.service.taskServiceImpl;

public class TestServiceClass {
	
	    @Mock
	    private UserBo userBo;  // Mock the UserBo dependency

	    @Mock
	    private UserMapper userMapper;  // Mock the UserMapper dependency

	    @InjectMocks
	    private taskServiceImpl taskService;  // Inject mocks into the service class

	    private UserDto userDto;
	    private User user;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this); // Initialize mocks
	        userDto = new UserDto(1, "lavanya13", "lavanya13@gmail.com", "lavanya13"); // Sample UserDto
	        user = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13"); // Sample User
	    }

	    // Test createUser when the user is successfully created
	    @Test
	    void testCreateUser_success() throws UserAlreadyExistsException {
	        when(userMapper.toUser(userDto)).thenReturn(user);  // Mock the userMapper to return the User
	        when(userBo.createUser(user)).thenReturn(user);  // Mock the userBo to return the created user

	        User createdUser = taskService.createUser(userDto);

	        // Verify the service logic
	        assertNotNull(createdUser);
	        assertEquals(user.getName(), createdUser.getName());
	        assertEquals(user.getEmail(), createdUser.getEmail());
	    }

	    // Test createUser when the user already exists
	    @Test
	    void testCreateUser_userAlreadyExists() throws UserAlreadyExistsException {
	        when(userMapper.toUser(userDto)).thenReturn(user);  // Mock the userMapper to return the User
	        when(userBo.createUser(user)).thenThrow(UserAlreadyExistsException.class);  // Simulate that user already exists

	        // Verify that the exception is thrown
	        assertThrows(UserAlreadyExistsException.class, () -> taskService.createUser(userDto));
	    }

	    // Test getAllUsers when users are present
	    @Test
	    void testGetAllUsers_success() {
	        when(userBo.getAllUsers()).thenReturn(List.of(user));  // Mock userBo to return a list of users

	        List<User> users = taskService.getAllUsers();

	        // Verify that the service returns the correct list of users
	        assertNotNull(users);
	        assertEquals(1, users.size());
	        assertEquals(user, users.get(0));
	    }

	    // Test getAllUsers when no users are present
	    @Test
	    void testGetAllUsers_empty() {
	        when(userBo.getAllUsers()).thenReturn(List.of());  // Mock userBo to return an empty list

	        List<User> users = taskService.getAllUsers();

	        // Verify that the returned list is empty
	        assertNotNull(users);
	        assertTrue(users.isEmpty());
	    }

	    // Test getByUserId when user is found
	    @Test
	    void testGetByUserId_success() throws UserNotFoundException {
	        int userId = 1;
	        when(userBo.getByUserId(userId)).thenReturn(Optional.of(user));  // Mock userBo to return a user

	        Optional<User> retrievedUser = taskService.getByUserId(userId);

	        // Verify the returned user is correct
	        assertTrue(retrievedUser.isPresent());
	        assertEquals(user, retrievedUser.get());
	    }

	    // Test getByUserId when user is not found
	    @Test
	    void testGetByUserId_userNotFound() throws UserNotFoundException {
	        int userId = 1;
	        when(userBo.getByUserId(userId)).thenReturn(Optional.empty());  // Mock userBo to return an empty Optional

	        // Verify that the exception is thrown
	        assertThrows(UserNotFoundException.class, () -> taskService.getByUserId(userId));
	    }
	    
}



