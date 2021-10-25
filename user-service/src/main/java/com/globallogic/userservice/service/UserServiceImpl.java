package com.globallogic.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.userservice.dao.UserRepository;
import com.globallogic.userservice.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByUserNameAndPassword(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	@Override
	public User updateUser(User user) {
		if (getUserById(user.getUserId()) == null)
			return null;
		return userRepository.save(user);
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

}
