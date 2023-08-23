package com.lucascode.helpdesk.services.exceptions;

public class ObjectNotFoundExcption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcption(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundExcption(String message) {
		super(message);
	}
	
	

}
