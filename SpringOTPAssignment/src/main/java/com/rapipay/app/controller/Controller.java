package com.rapipay.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rapipay.app.beans.User;
import com.rapipay.app.dao.OtpRepository;
import com.rapipay.app.exception.EmailNotFoundException;
import com.rapipay.app.exception.InvalidEmailException;
import com.rapipay.app.services.MailService;
import com.rapipay.app.services.SenderService;

@RestController
public class Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private MailService notificationService;
	
	@Autowired
	private OtpRepository otpRepo;


    @PostMapping(value="/generate")
    public String generate(@RequestBody User email) {
    	LOGGER.info("generate");
    	
    	return notificationService.addUser(email);
    	
    }
    

    @PostMapping(value="/validateOtp")
    public String validate(@RequestBody User user) {
    	LOGGER.info("inside validate");   	
    	return notificationService.validateOtp(user);
    	
    }
     
    @PostMapping(value="/resendOtp")
    public String resend(@RequestBody User user) throws EmailNotFoundException {
    	LOGGER.info("Inside resend otp");
    	return notificationService.resendOtp(user);
    }
   
}
