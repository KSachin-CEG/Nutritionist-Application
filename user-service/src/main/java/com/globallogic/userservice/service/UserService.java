package com.globallogic.userservice.service;

import com.globallogic.userservice.model.User;

public interface UserService {

	public User saveUser(User user);

	public User findByUserNameAndPassword(String userName, String password);

	public User updateUser(User user);

	public User getUserById(int userId);	

}
