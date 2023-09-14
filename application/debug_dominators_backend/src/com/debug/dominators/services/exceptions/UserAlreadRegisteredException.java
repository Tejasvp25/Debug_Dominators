package com.debug.dominators.services.exceptions;

public class UserAlreadRegisteredException extends Exception {

	public UserAlreadRegisteredException() {
		super("User already registered");
	}

}
