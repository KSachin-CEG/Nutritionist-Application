package com.globallogic.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.userservice.model.User;
import com.globallogic.userservice.service.UserService;

@RestController
@RequestMapping("api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			User newUser = userService.saveUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Message :" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<?> loginUser(@PathVariable String userName, @PathVariable String password) {
		try {
			if (userName == null || password == null) {
				throw new Exception("Username or Password can not be empty");
			}
			User user = userService.findByUserNameAndPassword(userName, password);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Message" + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		return new ResponseEntity<>("User logged out successfully", HttpStatus.OK);
	}

	@PutMapping(value = "/users", consumes = { "application/xml", "application/json" })
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		if (updatedUser == null)
			return new ResponseEntity<>("User with ID : " + user.getUserId() + " is not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {
		User reqUser = userService.getUserById(userId);
		if (reqUser == null)
			return new ResponseEntity<>("User with ID : " + userId + " is not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reqUser, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@DeleteMapping("users/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
		User deletedUser = userService.deleteUserById(userId);
		if (deletedUser == null)
			return new ResponseEntity<>("User with ID : " + userId + " is not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(deletedUser, HttpStatus.OK);
	}
}
