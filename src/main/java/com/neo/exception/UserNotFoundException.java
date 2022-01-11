package com.neo.exception;

import javax.security.sasl.AuthenticationException;

public class UserNotFoundException  extends RuntimeException{
	
	private static final long serialVersionUid=1l;
	
	
	
	public UserNotFoundException(String message) {
        super(message);
    }

   
}
