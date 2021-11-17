package com.rapipay.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InvalidOtpException extends Exception {

private static final long serialVersionID = 1L;
	
	public InvalidOtpException(String message) 
	{
		super(message);
	}
	
}
