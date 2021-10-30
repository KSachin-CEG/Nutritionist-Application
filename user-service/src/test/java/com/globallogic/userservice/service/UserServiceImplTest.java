package com.globallogic.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.userservice.dao.UserRepository;
import com.globallogic.userservice.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;
	private List<User> userList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		user = new User(1, "Shriya", "Qwerty@123", new Date(1997-05-18), "Female", "India", "North", "Veg");
		userList.add(user);
	}

	@AfterEach
	public void tearDown() {
		user = null;
		userList = null;
	}

	@Test
	public void givenValidUserToSaveThenShouldReturnSavedUser() throws Exception {
		when(userRepository.save(user)).thenReturn(user);
		User addedUser = userService.saveUser(user);
		assertEquals(user, addedUser);
		verify(userRepository, times(1)).save(any());
	}

	@Test
	public void givenValidUserNameAndPasswordThenShouldResturnRespectiveUser() throws Exception {
		when(userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword())).thenReturn(user);
		User userResult = userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		assertNotNull(userResult);
		assertEquals(user, userResult);
		verify(userRepository, times(1)).findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}

	@Test
	public void givenValidUserIDThenShouldReturnRespectiveUser() throws Exception {
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		User retreivedUser = userService.getUserById(1);
		assertEquals(user, retreivedUser);
		verify(userRepository, times(1)).findById(anyInt());
	}

	@Test
	public void givenUserToUpdateThenShouldReturnUpdatedUser() {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);
		user.setRegion("South");
		User updatedUser = userService.updateUser(user);
		assertEquals(updatedUser.getRegion(), "South");
		verify(userRepository, times(1)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void givenInvalidUSerToUpdateThenShouldNotReturnUpdatedUSer() {
		when(userRepository.findById(user.getUserId())).thenReturn(null);
		User upadtedUser = userService.updateUser(user);
		assertNull(upadtedUser);
		verify(userRepository, times(0)).save(user);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
		when(userRepository.findAll()).thenReturn(userList);
		List<User> userList1 = userService.getAllUsers();
		assertEquals(userList, userList1);
		verify(userRepository, times(1)).findAll();
		assertEquals(userList.size(), userList1.size());
	}

	@Test
	void givenUserIdToDeleteThenShouldReturnDeletedUser() {
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		User deletedUser = userService.deleteUserById(1);
		assertEquals(1, deletedUser.getUserId());
		verify(userRepository, times(2)).findById(user.getUserId());
		verify(userRepository, times(1)).deleteById(user.getUserId());
	}

	@Test
	void givenUserIdToDeleteThenShouldNotReturnDeletedUser() {
		when(userRepository.findById(2)).thenReturn(null);
		User deletedUser = userService.deleteUserById(2);
		verify(userRepository, times(1)).findById(2);
		verify(userRepository, times(0)).deleteById(user.getUserId());
		assertNull(deletedUser);
	}

}
