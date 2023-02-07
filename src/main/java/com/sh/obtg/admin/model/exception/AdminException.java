package com.sh.obtg.admin.model.exception;

public class AdminException extends RuntimeException{

	public AdminException() {
		super();
	}

	public AdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdminException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdminException(String message) {
		super(message);
	}

	public AdminException(Throwable cause) {
		super(cause);
	}
	
	

}
