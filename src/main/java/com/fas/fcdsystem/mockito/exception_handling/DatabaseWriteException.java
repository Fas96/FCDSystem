package com.fas.fcdsystem.mockito.exception_handling;

public class DatabaseWriteException extends RuntimeException {
	public DatabaseWriteException(String message) {
		super(message);
	}
}
