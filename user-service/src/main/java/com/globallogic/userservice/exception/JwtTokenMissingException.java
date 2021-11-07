package com.globallogic.userservice.exception;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
	
	private static final long serialVersionUID = 1L;

	public JwtTokenMissingException(String message) {
		super(message);
	}

}
