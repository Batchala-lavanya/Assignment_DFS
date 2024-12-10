package com.demo.task.Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.task.Bo.UserBoImpl;
import com.demo.task.Dao.taskRepository;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserAlreadyExistsException;
import com.demo.task.Exceptions.UserNotFoundException;

public class TestingBo {

    @Mock
    private taskRepository taskRepo;

    @InjectMocks
    private UserBoImpl userBo;

    private User user;

    // @BeforeEach - Setup for test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize the User object
        user = new User(1, "lavanya13", "lavanya13@gmail.com", "lavanya13");
    }

    
    @Test
    public void testCreateUser_success() {  
        when(taskRepo.save(user)).thenReturn(user);
        
        User createdUser = userBo.createUser(user);
       
        assertEquals(user,createdUser);    
    }
    
    @Test
    void testCreateUser_userAlreadyExists() throws UserAlreadyExistsException {
        when(taskRepo.existsById(user.getUserId())).thenReturn(true); // User already exists

        
        assertThrows(UserAlreadyExistsException.class, () -> userBo.createUser(user));

        verify(taskRepo, never()).save(user); // Ensure save() was not called
    }
    
    @Test
    void testGetByUserId_success() throws UserNotFoundException {
        int userId = 1;
        when(taskRepo.findById(userId)).thenReturn(Optional.of(user)); // User found

        Optional<User> result = userBo.getByUserId(userId);

        
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
    
    @Test
    void testGetByUserId_userNotFound() throws UserNotFoundException {
        int userId = 1;
        when(taskRepo.findById(userId)).thenReturn(Optional.empty()); // User not found

        
        assertThrows(UserNotFoundException.class, () -> userBo.getByUserId(userId));
    }
    
    @Test
    void testGetAllUsers_success() {
        when(taskRepo.findAll()).thenReturn(List.of(user)); // Return a list with one user

        List<User> users = userBo.getAllUsers();

        
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(user, users.get(0));
    }
    
    @Test
    void testGetAllUsers_empty() {
        when(taskRepo.findAll()).thenReturn(List.of()); // Return an empty list

        List<User> users = userBo.getAllUsers();

       
        assertNotNull(users);
        assertTrue(users.isEmpty());
    }
    
}

