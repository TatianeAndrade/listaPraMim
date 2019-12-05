package com.listaPraMim.exceptions;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserNotExistsException extends AuthenticationCredentialsNotFoundException{
	
	public FirebaseUserNotExistsException() {
		super("User not Fount");
	}
	
	private static final long serialVersionUID = 789949671713648425L;
}
