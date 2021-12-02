package com.accela.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<ErrorMessage> personNotFoundException(final PersonNotFoundException personNotFoundException, 
			final WebRequest webRequest) {
		
		final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, personNotFoundException.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorMessage> addressNotFoundException(final AddressNotFoundException addressNotFoundException, 
			final WebRequest webRequest) {
		
		final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, 
				addressNotFoundException.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> illegalArgumentException(final IllegalArgumentException illegalArgumentException, 
			final WebRequest webRequest) {
		
		final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,
				illegalArgumentException.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		
	}

}
