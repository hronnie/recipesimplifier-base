package com.codeproj.recipesimplifierbase.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);

	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
		logger.info("Validation exception has been thrown");
	}

	public ValidationException(String message) {
		super(message);
		logger.info("Validation exception has been thrown with the following message>> " + message);
		
	}
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
		logger.info("Validation exception has been thrown with the following message>> " + message);
	}
	
	public ValidationException(Throwable cause) {
		super(cause);
		logger.info("Validation exception has been thrown");
	}
}

