package com.codeproj.recipesimplifierbase.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseEntityNotFoundException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseEntityNotFoundException.class);
	private static final long serialVersionUID = 1L;

	public DatabaseEntityNotFoundException() {
		super();
		logger.info("DatabaseEntityNotFoundException exception has been thrown");
	}

	public DatabaseEntityNotFoundException(String message) {
		super(message);
		logger.info("DatabaseEntityNotFoundException exception has been thrown with the following message>> " + message);
	}
	
	public DatabaseEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		logger.info("DatabaseEntityNotFoundException exception has been thrown with the following message>> " + message);
	}
	
	public DatabaseEntityNotFoundException(Throwable cause) {
		super(cause);
		logger.info("DatabaseEntityNotFoundException exception has been thrown");
	}
}
