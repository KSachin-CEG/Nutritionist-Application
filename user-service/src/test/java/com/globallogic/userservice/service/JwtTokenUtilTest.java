package com.globallogic.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Base64;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.userservice.model.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@ExtendWith(MockitoExtension.class)
class JwtTokenUtilTest {

	private String secret = "Covid19";
	
	private String secretKey = Base64.getEncoder().encodeToString(secret.getBytes());

	@InjectMocks
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	private JwtBuilder jwtBuilder;

	private User user;

	@BeforeEach
	public void setUp() {
		user = new User(1, "Shriya", "Qwerty@123", new Date(1997 - 05 - 18), "Female", "India", "North", "Veg");
	}

	@AfterEach
	public void tearDown() {
		user = null;
	}

	@Test
	public void whenGenerateTokenThenShouldCallDoGenerateToken() {		
		/*
		 * try (MockedStatic<Jwts> jwts = Mockito.mockStatic(Jwts.class)) { jwts.when(()
		 * -> Jwts.builder()).thenReturn(jwtBuilder);
		 * when(jwtBuilder.setClaims(anyMap())).thenReturn(jwtBuilder);
		 * when(jwtBuilder.setSubject(anyString())).thenReturn(jwtBuilder);
		 * when(jwtBuilder.setIssuedAt(any())).thenReturn(jwtBuilder);
		 * when(jwtBuilder.setExpiration(any())).thenReturn(jwtBuilder);
		 * when(jwtBuilder.signWith(SignatureAlgorithm.HS512,
		 * secretKey)).thenReturn(jwtBuilder);
		 * when(jwtBuilder.compact()).thenReturn("token");
		 * 
		 * } String response = jwtTokenUtil.generateToken(user); assertEquals("token",
		 * response);
		 */

	}

}
