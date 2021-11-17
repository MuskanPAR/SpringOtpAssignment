package com.rapipay.app.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rapipay.app.beans.User;
import com.rapipay.app.exception.EmailNotFoundException;

public interface MailService {
	
	public boolean validEmail(String email);
	
	public String addUser(User user);

	public String validateOtp(User user);

	public String resendOtp(User data) throws EmailNotFoundException;

	//public String resendOtp() throws EmailNotFoundException;

}
