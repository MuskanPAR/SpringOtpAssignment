package com.rapipay.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String sendEmail(String toEmail,String body,String subject) {
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("muskanparveen761@gmail.com");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);

			System.out.println("running");
			javaMailSender.send(message);
			return "Send mail";
	}
	
	
}
