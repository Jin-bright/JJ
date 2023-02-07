package com.sh.obtg.faq.model.exception;

public class FaqException extends RuntimeException{

	public FaqException() {
		super();
	}

	public FaqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FaqException(String message, Throwable cause) {
		super(message, cause);
	}

	public FaqException(String message) {
		super(message);
	}

	public FaqException(Throwable cause) {
		super(cause);
	}

	
	
}
