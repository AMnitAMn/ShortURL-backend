package com.capg.shorturl.exception;

public class LinkNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LinkNotFoundException() {
	
	}
	
	public LinkNotFoundException(String message) {
		super(message);
	}
}
