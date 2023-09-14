package com.debug.dominators.services.exceptions;

public class InvalidUsernamePasswordException extends Exception {
	public InvalidUsernamePasswordException() {
		super("Username or Password is Not Valid");
	}
}
