package com.holidu.interview.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error in parsing api data
 * json response from 3rd party data providers
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error in parsing api data.")
public class DataParseException extends Exception {

    private static final long serialVersionUID = -3387516993224229948L;

    public DataParseException(String message) {
        super(message);
    }
}
