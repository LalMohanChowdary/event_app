package org.jsp.eventapp.exceptionHandler;

import org.jsp.eventapp.exceptionclasses.InvalidEventIdException;
import org.jsp.eventapp.exceptionclasses.NoEventFoundException;
import org.jsp.eventapp.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {

	@ExceptionHandler(NoEventFoundException.class)
	public ResponseEntity<?> noEventFoundExceptionHandler(NoEventFoundException e) {

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.message("No Event Found")
				.body(e.getMessage()).build());
	}
	@ExceptionHandler(InvalidEventIdException.class)
	public ResponseEntity<?> invalidEventIdException(InvalidEventIdException e) {

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.message("Invalid Id ")
				.body(e.getMessage()).build());
	}

}
