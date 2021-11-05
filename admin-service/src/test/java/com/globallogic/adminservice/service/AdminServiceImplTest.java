package com.globallogic.adminservice.service;

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

import com.globallogic.adminservice.dao.AdminRepository;
import com.globallogic.adminservice.model.User;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

	@Mock
	private AdminRepository adminRepository;

	@InjectMocks
	private AdminServiceImpl adminService;

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
	public void givenValidUserIDThenShouldReturnRespectiveUser() throws Exception {
		when(adminRepository.findById(1)).thenReturn(Optional.of(user));
		User retreivedUser = adminService.getUserById(1);
		assertEquals(user, retreivedUser);
		verify(adminRepository, times(1)).findById(anyInt());
	}


	@Test
	public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
		when(adminRepository.findAll()).thenReturn(userList);
		List<User> userList1 = adminService.getAllUsers();
		assertEquals(userList, userList1);
		verify(adminRepository, times(1)).findAll();
		assertEquals(userList.size(), userList1.size());
	}

	@Test
	void givenUserIdToDeleteThenShouldReturnDeletedUser() {
		when(adminRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		User deletedUser = adminService.deleteUserById(1);
		assertEquals(1, deletedUser.getUserId());
		verify(adminRepository, times(2)).findById(user.getUserId());
		verify(adminRepository, times(1)).deleteById(user.getUserId());
	}

	@Test
	void givenUserIdToDeleteThenShouldNotReturnDeletedUser() {
		when(adminRepository.findById(2)).thenReturn(null);
		User deletedUser = adminService.deleteUserById(2);
		verify(adminRepository, times(1)).findById(2);
		verify(adminRepository, times(0)).deleteById(user.getUserId());
		assertNull(deletedUser);
	}

}
