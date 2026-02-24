package com.info.exception;

public class UserProfileNotFoundException  extends RuntimeException{
	public UserProfileNotFoundException(String msg) {
		super(msg);
	}
}
