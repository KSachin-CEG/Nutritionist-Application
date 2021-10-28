package com.globallogic.userservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userService;
	
	User user;
	
	 @BeforeEach
	 public void setUp(){
		 user=new User(1,"Shriya","Qwerty@123",new Date(1997-05-18),"Female","India","North","Veg"); 
		 
	   }
	 
	 @Test
	 public void givenValidUserIDThenShouldReturnUser() throws Exception {
	        
	        Optional<User> optionalUser = Optional.of(user);
	        when(userRepository.findById(1)).thenReturn(optionalUser);
	        User retreivedEmployee = userService.getUserById(1);
	        assertEquals(user.getUserId(),retreivedEmployee.getUserId());
	    }
	 
	    @Test
		public void givenUserThenShouldReturnSavedUser() throws Exception {
			when(userRepository.save(user)).thenReturn(user);
			User addedUser = userService.saveUser(user);
			assertEquals("Shriya", addedUser.getUserName());
			verify(userRepository, times(1)).save(user);
		}
	 
		@Test
		public void givenValidUserNameAndPasswordThenShouldCheckIt() throws Exception {
			when(userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword())).thenReturn(user);
			User userResult = userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
			assertNotNull(userResult);
			assertEquals("Shriya", userResult.getUserName());
			verify(userRepository, times(1)).findByUserNameAndPassword(user.getUserName(), user.getPassword());

		}
		
		@Test
		public void whenGivenId_shouldReturnUser_ifFound() {
			List<User> userList=new ArrayList<>();
			userList.add(user);
			when(userRepository.findAll()).thenReturn(userList);
			List<User> expected = userService.getAllUsers();
			assertNotNull(userList);
			assertThat(expected).isSameAs(userList);
			
			}
		
		
	 


	
	

}
