package com.globallogic.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
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
import com.globallogic.userservice.model.User;
import com.globallogic.userservice.service.JwtTokenUtil;
import com.globallogic.userservice.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class UserControllerTest {

	@MockBean
	private UserService userService;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@InjectMocks
	private UserController userController;

	@Autowired
	private MockMvc mockMvc;

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
	public void givenNewUserToRegisterThenShouldReturnUserCreatedHttpCode() throws Exception {
		when(userService.saveUser(any())).thenReturn(user);
		mockMvc.perform(post("/api/v1/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated());
		verify(userService, times(1)).saveUser(any());
	}

	@Test
	public void givenNewUserToRegisterThenShouldReturnSavedUser() throws Exception {
		when(userService.saveUser(any())).thenReturn(user);
		mockMvc.perform(post("/api/v1/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated());
		User newUser = userService.saveUser(user);
		assertEquals(user.getUserId(), newUser.getUserId());
		assertEquals(user.getUserName(), newUser.getUserName());
	}

	@Test
	public void givenDuplicateUserToRegisterThenShouldReturnConflictHttpCode() throws Exception {
		when(userService.saveUser(any())).thenReturn(user);
		when(userService.saveUser(user)).thenThrow(RuntimeException.class);
		mockMvc.perform(post("/api/v1/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict());
		verify(userService, times(1)).saveUser(user);
	}

	@Test
	public void givenValidUserDetailsThenLoginUser() throws Exception {
		when(userService.saveUser(user)).thenReturn(user);
		when(userService.findByUserNameAndPassword(user.getUserName(), user.getPassword())).thenReturn(user);
		mockMvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).findByUserNameAndPassword(user.getUserName(), user.getPassword());
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void givenInvalidUserDetailsThenShouldReturnNotFoundHttpCode() throws Exception {
		User invalidUser = new User(2, "Sachin", "pa$$woRD", new Date(1997 - 05 - 18), "Male", "India", "North",
				"NonVeg");
		when(userService.findByUserNameAndPassword(invalidUser.getUserName(), invalidUser.getPassword()))
				.thenReturn(null);
		mockMvc.perform(
				post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(invalidUser)))
				.andExpect(status().isNotFound());
		verify(userService, times(1)).findByUserNameAndPassword(invalidUser.getUserName(), invalidUser.getPassword());
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void givenUnacceptedUserDetailsThenShouldReturnBadRequestHttpCode() throws Exception {
		User unacceptedUser = null;
		mockMvc.perform(
				post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(unacceptedUser)))
				.andExpect(status().isBadRequest());
		verify(userService, times(0)).saveUser(user);
	}

	@Test
	public void givenValidUserIdThenShouldReturnRespectiveUser() throws Exception {
		when(userService.getUserById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isOk());
		verify(userService, times(1)).getUserById(user.getUserId());
	}

	@Test
	public void givenInValidUserIdThenShouldReturnNotFoundHttpCode() throws Exception {
		when(userService.getUserById(user.getUserId())).thenReturn(null);
		mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isNotFound());
		verify(userService, times(1)).getUserById(user.getUserId());
	}

	@Test
	public void givenGetAllUsersThenShouldReturnUserList() throws Exception {
		when(userService.getAllUsers()).thenReturn(userList);
		mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
		verify(userService).getAllUsers();
		verify(userService, times(1)).getAllUsers();
	}

	@Test
	public void givenValidUserToUpdateThenShouldReturnUpdatedUser() throws Exception {
		when(userService.updateUser(user)).thenReturn(user);
		mockMvc.perform(put("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
		verify(userService, times(1)).updateUser(user);
	}

	@Test
	public void givenInvalidUserToUpdateThenShouldReturnNotFoundHttpCode() throws Exception {
		when(userService.updateUser(user)).thenReturn(null);
		mockMvc.perform(put("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isNotFound());
		verify(userService, times(1)).updateUser(user);
	}

	@Test
	public void givenValidUserToDeleteThenShouldReturnUpdatedUser() throws Exception {
		when(userService.deleteUserById(user.getUserId())).thenReturn(user);
		mockMvc.perform(delete("/api/v1/users/1")).andExpect(status().isOk());
		verify(userService, times(1)).deleteUserById(user.getUserId());
	}

	@Test
	public void givenInvalidUserToDeleteThenShouldReturnNotFoundHttpCode() throws Exception {
		when(userService.deleteUserById(user.getUserId())).thenReturn(null);
		mockMvc.perform(delete("/api/v1/users/1")).andExpect(status().isNotFound());
		verify(userService, times(1)).deleteUserById(user.getUserId());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
