package com.debug.dominators.services.exceptions;

public class PermissionException extends Exception {

	public PermissionException() {
		super("You don't have required permission to perform this action");
	}

}
