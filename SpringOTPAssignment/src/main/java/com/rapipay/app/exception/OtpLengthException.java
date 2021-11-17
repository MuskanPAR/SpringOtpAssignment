package com.rapipay.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class OtpLengthException extends Exception {

private static final long serialVersionID = 1L;
	
	public OtpLengthException(String message) 
	{
		super(message);
	}
	
}
