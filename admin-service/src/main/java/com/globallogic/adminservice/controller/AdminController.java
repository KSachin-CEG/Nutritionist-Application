package com.globallogic.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.adminservice.model.User;
import com.globallogic.adminservice.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable int userId) {
		User reqUser = adminService.getUserById(userId);
		if (reqUser == null)
			return new ResponseEntity<>("User with ID : " + userId + " is not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reqUser, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = adminService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
		User deletedUser = adminService.deleteUserById(userId);
		if (deletedUser == null)
			return new ResponseEntity<>("User with ID : " + userId + " is not found", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(deletedUser, HttpStatus.OK);
	}
}
