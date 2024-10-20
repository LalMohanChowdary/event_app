package org.jsp.eventapp.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class InvalidEventIdException extends RuntimeException {
	
	private String message;

	public InvalidEventIdException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
	

}
