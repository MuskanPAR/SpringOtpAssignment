package com.rapipay.app.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rapipay.app.beans.User;
import com.rapipay.app.controller.Controller;
import com.rapipay.app.dao.OtpRepository;
import com.rapipay.app.exception.EmailNotFoundException;
import com.rapipay.app.exception.InvalidEmailException;
import com.rapipay.app.exception.InvalidOtpException;
import com.rapipay.app.exception.OtpLengthException;


@Service
public class MailServiceImpl implements MailService{
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	@Value("${attempts}")
	private int maxattempt;
	
	@Autowired
	private OtpRepository otpRepo;

	@Autowired
	private SenderService senderService;
	
	@Override
	public boolean validEmail(String email) {
		System.out.println(maxattempt+"-------------------------");
		final Pattern EMAIL_REGEX = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", Pattern.CASE_INSENSITIVE);
	    return EMAIL_REGEX.matcher(email).matches();
	}
	

	@Override
	public String addUser(User data)  {
		// TODO Auto-generated method stub
		LOGGER.info("Inside addUser");
		
		if(validEmail(data.getEmail())==false) {
    		return new InvalidEmailException("Wrong email pattern").toString();
    	}
		data.setOtpRequestTime();
    	int otp = new Random().nextInt(999999);
    	data.setOtp(otp);
		otpRepo.save(data);
    	String send=senderService.sendEmail(data.getEmail(), String.format("%06d", otp),"System generated otp");
		return "Otp sent";
		
	}
	

	@Override
	public String validateOtp(User user) {
		// TODO Auto-generated method stub
		LOGGER.info("Inside ValidateOTP");
		String email=user.getEmail();
		
		if(validEmail(email)==false) {
    		return new InvalidEmailException("Wrong email pattern").toString();
    	}
		
    	int otp=user.getOtp();
    	String otpData=String.valueOf(otp);
    	
    	try {
    		
    		User data= otpRepo.findById(email).orElseThrow(() -> new EmailNotFoundException("Email not  found"));
    		
    		System.out.println(data.getOtpRequestTime());
    		System.out.println(System.currentTimeMillis());
    		
        	long time=System.currentTimeMillis();
        	long diff=( time-data.getOtpRequestTime() )/1000;

        	System.out.println("difference is "+diff);
        	
        	System.out.println("inside validate");
        	
        	if(diff<60 && data.getOtp()==otp) {
        		System.out.println("running inside if");
        		return "Correct Otp..Verified";
        	}
        	
        	else if(diff>60) {
        		return "Otp expired";
        	}
        	
        	else if(otpData.length()!=6){
        		return new OtpLengthException("Otp length is not of six digits").toString();
        	}
        	
        	else {
        		System.out.println("running inside else");
        		return new InvalidOtpException("Incorrect otp..try again").toString();
        	}
    		
    	}
    	catch(EmailNotFoundException e) {
    		return e.getMessage();
    	}
    	
    	
	}
	
	
	@Override
	public String resendOtp(User data) {
		// TODO Auto-generated method stub
		
			
		String email=data.getEmail();
		System.out.println(email);
		
		if(validEmail(email)==false) {
    		return new InvalidEmailException("Wrong email pattern").toString();
    	}
		try {
				
			User user= otpRepo.findById(email).orElseThrow(() -> new EmailNotFoundException("Email not  found"));
			System.out.println(user.getOtpRequestTime());
			System.out.println(System.currentTimeMillis());
			if( (maxattempt>=1)) {
				
				maxattempt--;
				System.out.println(maxattempt);
				int otp = new Random().nextInt(999999);
				
				if( (System.currentTimeMillis()-user.getOtpRequestTime()/1000) >60) {
					
					data.setOtp(otp);
					data.setOtpRequestTime();
					otpRepo.save(data);
					senderService.sendEmail(data.getEmail(), String.format("%06d", data.getOtp()),"System generated otp");
					
					return "Otp sent successfully";
					
				}
				
				else {
					
					return "You can generate otp only after 60 secs";
					
				}
				
			}
			else {
				System.out.println(maxattempt);
				return "Max limit reached..generate otp again";
				
			}
			
			
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
		
		
	}

	
	
	

	
	
	
	
}
