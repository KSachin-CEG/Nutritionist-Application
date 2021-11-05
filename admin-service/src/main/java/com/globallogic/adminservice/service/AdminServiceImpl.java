package com.globallogic.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.adminservice.dao.AdminRepository;
import com.globallogic.adminservice.model.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public User getUserById(int userId) {
		return adminRepository.findById(userId).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return adminRepository.findAll();
	}

	@Override
	public User deleteUserById(int userId) {
		if (adminRepository.findById(userId) == null)
			return null;
		User deletedUser = getUserById(userId);
		adminRepository.deleteById(userId);
		return deletedUser;
	}

}
