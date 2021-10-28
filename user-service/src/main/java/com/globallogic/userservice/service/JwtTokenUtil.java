package com.globallogic.userservice.service;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.globallogic.userservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 251;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// generate token for user
	public String generateToken(User userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, Integer.toString(userDetails.getUserId()), userDetails.getPassword());
	}

	private String doGenerateToken(Map<String, Object> claims, String id, String password) {

		return Jwts.builder().setClaims(claims).setSubject(id).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}

