package com.rest.exception;

public class CustomRestaurantException extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomRestaurantException(String message) {
		super(message);
	}
}
