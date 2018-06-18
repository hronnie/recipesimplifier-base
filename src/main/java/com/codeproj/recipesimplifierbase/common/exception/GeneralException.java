package com.codeproj.recipesimplifierbase.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GeneralException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(GeneralException.class);
	
	private static final long serialVersionUID = -8513108449365813032L;

	public GeneralException() {
		super();
		logger.info("GeneralException exception has been thrown");
	}

	public GeneralException(String message) {
		super(message);
		logger.info("GeneralException exception has been thrown with the following message>> " + message);
	}
	
	public GeneralException(String message, Throwable cause) {
		super(message, cause);
		logger.info("GeneralException exception has been thrown with the following message>> " + message);
	}
	
	public GeneralException(Throwable cause) {
		super(cause);
		logger.info("GeneralException exception has been thrown");
	}
}
