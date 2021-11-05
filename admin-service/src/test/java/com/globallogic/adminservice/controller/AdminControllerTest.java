package com.globallogic.adminservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.adminservice.model.User;
import com.globallogic.adminservice.service.AdminService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class AdminControllerTest {

	@MockBean
	private AdminService adminService;

	@InjectMocks
	private AdminController adminController;

	@Autowired
	private MockMvc mockMvc;

	private User user;
	private List<User> userList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		user = new User(1, "Shriya", "Qwerty@123", new Date(1997 - 05 - 18), "Female", "India", "North", "Veg");
		userList.add(user);
	}

	@AfterEach
	public void tearDown() {
		user = null;
		userList = null;
	}

	@Test
	public void givenValidUserIdThenShouldReturnRespectiveUser() throws Exception {
		when(adminService.getUserById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/api/v1/admin/1")).andExpect(status().isOk());
		verify(adminService, times(1)).getUserById(user.getUserId());
	}

	@Test
	public void givenInValidUserIdThenShouldReturnNotFoundHttpCode() throws Exception {
		when(adminService.getUserById(user.getUserId())).thenReturn(null);
		mockMvc.perform(get("/api/v1/admin/1")).andExpect(status().isNotFound());
		verify(adminService, times(1)).getUserById(user.getUserId());
	}

	@Test
	public void givenGetAllUsersThenShouldReturnUserList() throws Exception {
		when(adminService.getAllUsers()).thenReturn(userList);
		mockMvc.perform(get("/api/v1/admin")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
		verify(adminService).getAllUsers();
		verify(adminService, times(1)).getAllUsers();
	}

	@Test
	public void givenValidUserToDeleteThenShouldReturnUpdatedUser() throws Exception {
		when(adminService.deleteUserById(user.getUserId())).thenReturn(user);
		mockMvc.perform(delete("/api/v1/admin/1")).andExpect(status().isOk());
		verify(adminService, times(1)).deleteUserById(user.getUserId());
	}

	@Test
	public void givenInvalidUserToDeleteThenShouldReturnNotFoundHttpCode() throws Exception {
		when(adminService.deleteUserById(user.getUserId())).thenReturn(null);
		mockMvc.perform(delete("/api/v1/admin/1")).andExpect(status().isNotFound());
		verify(adminService, times(1)).deleteUserById(user.getUserId());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
