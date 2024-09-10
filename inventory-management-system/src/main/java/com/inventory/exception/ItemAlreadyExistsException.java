package com.inventory.exception;

public class ItemAlreadyExistsException extends RuntimeException{

	public ItemAlreadyExistsException(String message) {
		super(message);
	}

}
