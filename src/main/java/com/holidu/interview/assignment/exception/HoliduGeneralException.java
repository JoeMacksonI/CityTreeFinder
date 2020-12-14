package com.holidu.interview.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * Generic Exception
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error in processing request.")
public class HoliduGeneralException extends Exception {
	static final long serialVersionUID = -3387516993224229948L;

	public HoliduGeneralException(String message) {
		super(message);
	}

}
