package com.globallogic.userservice.service;

import static org.mockito.Mockito.doCallRealMethod;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.userservice.model.User;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

	private Map<String, Object> claims = new HashMap<>();
	private User user;

	@Test
	public void testDoGenerateTokenCalledFromGenerateToken() {
		user = new User(1, "Shriya", "Qwerty@123", new Date(1997 - 05 - 18), "Female", "India", "North", "Veg");
		JwtTokenUtil jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
		doCallRealMethod().when(jwtTokenUtil).generateToken(user);
		jwtTokenUtil.generateToken(user);
		Mockito.verify(jwtTokenUtil).doGenerateToken(claims, Integer.toString(user.getUserId()), user.getPassword());
	}
}
