package com.demo.task.ThymeLeafContoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.task.Dto.UserDto;
import com.demo.task.EntityVO.User;
import com.demo.task.Exceptions.UserNotFoundException;
import com.demo.task.service.taskService;

@Controller
@RequestMapping("/api/myassignment")
public class UserThymeleafController {
	
	@Autowired
	private taskService taskSer;

	// Add this if UserDto is not initialized
	private UserDto userdto = new UserDto(); 
	
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,@RequestParam("password") String password, Model model) {
		try {
			boolean isAuthenticated=taskSer.authenticate(email, password);
			
			if(isAuthenticated) {
				return "HomePage.html";
			}
			return "login.html";
			
		}catch(UserNotFoundException e){
			model.addAttribute("error","userNot Found");
			return "login.html";
		}
		
		
	}
	
	// Register the user
	@GetMapping("/registerUser")
	public String showRegistrationPage() {
		return "RegistrationPage.html";
	}
	
	@PostMapping("/registerUser")
	public String userRegistration(@RequestParam("name") String name, @RequestParam("email") String email, 
	                                @RequestParam("password") String password, Model model) {
		try {
			userdto.setName(name);
			userdto.setEmail(email);
			userdto.setPassword(password);
			taskSer.createUser(userdto); // Save the user
			model.addAttribute("user", userdto);
			return "Register-success.html"; // Registration success page
		} catch (Exception e) {
			model.addAttribute("error", "User registration failed");
			return "RegistrationPage.html"; // Registration failed page
		}
	}
	
	// Show user by ID
	@GetMapping("/UserById")
    public String showGetUserById() {
        return "UserById";  // Redirect to page to get User ID
    }
 
    @GetMapping("/ViewUserById")
    public String showGetUserById(@RequestParam("id") int id, Model model) {
        // Fetch a user by ID
    	Optional<User> user = taskSer.getByUserId(id);

        if (user.isPresent()) {
            model.addAttribute("user", user.get()); // Add user to the model
            return "Profile.html";  // Display profile page
        } else {
            model.addAttribute("error", "User not found");
            return "UserById.html";  // Return to UserById page if not found
        }
    }
	
    // Show all users
    @GetMapping("/AllUsers")
    public String showGetAllUsers(Model model) {
        List<User> userList = taskSer.getAllUsers();  // Assuming a method to fetch all users
        model.addAttribute("users", userList);  // Add all users to the model
        return "AllUsersDetails.html";  // Display all users page
    }
}
