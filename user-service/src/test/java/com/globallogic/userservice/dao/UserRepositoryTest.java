package com.globallogic.userservice.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.globallogic.userservice.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void givenInValidUserIdThenReturnEmptyOptional() {
		assertTrue(userRepository.findById(-1).isEmpty());
	}

	@Test
	public void givenValidUserIdThenReturnUserOptional() {
		User user = new User(1, "Shriya", "Qwerty@123", new Date(1997 - 05 - 18), "Female", "India", "North", "Veg");
		userRepository.save(user);
		assertTrue(userRepository.findById(1).isPresent());
	}

	@Test
	public void givenUserToSaveThenShouldReturnSavedUser() {
		User user = new User(2, "Sachin", "Qwerty@123", new Date(1997 - 05 - 18), "Male", "India", "North", "Veg");
		User addedUser = userRepository.save(user);
		assertEquals(user.getUserId(), addedUser.getUserId());
	}
}