package com.accela.exercise.exception;

public class PersonNotFoundException extends Exception {

	private static final long serialVersionUID = -4119259060132620717L;

	public PersonNotFoundException() {
		super();
	}

	public PersonNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersonNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersonNotFoundException(String message) {
		super(message);
	}

	public PersonNotFoundException(Throwable cause) {
		super(cause);
	}

}
