package com.globallogic.adminservice.service;

import java.util.List;

import com.globallogic.adminservice.model.User;

public interface AdminService {

	public User getUserById(int userId);

	public List<User> getAllUsers();

	public User deleteUserById(int userId);	

}
