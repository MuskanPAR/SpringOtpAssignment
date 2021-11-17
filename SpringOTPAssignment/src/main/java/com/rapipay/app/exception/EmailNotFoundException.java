package com.rapipay.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends Exception{

	private static final long serialVersionID = 1L;
	
	public EmailNotFoundException(String message) 
	{
		super(message);
	}
	
	
}
