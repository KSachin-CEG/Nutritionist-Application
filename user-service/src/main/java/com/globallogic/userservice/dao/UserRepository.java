package com.globallogic.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserNameAndPassword(String userName, String password);

}
