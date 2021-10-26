package com.globallogic.userservice.service;

import java.util.List;

import com.globallogic.userservice.model.User;

public interface UserService {

	public User saveUser(User user);

	public User findByUserNameAndPassword(String userName, String password);

	public User updateUser(User user);

	public User getUserById(int userId);

	public List<User> getAllUsers();

	public User deleteUserById(int userId);	

}
