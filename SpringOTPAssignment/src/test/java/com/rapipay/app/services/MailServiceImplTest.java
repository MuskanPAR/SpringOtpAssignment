package com.rapipay.app.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rapipay.app.beans.User;
import com.rapipay.app.dao.OtpRepository;

class MailServiceImplTest {

	@Autowired
	private MailService service;
	
	@MockBean
	private OtpRepository dao;
	
	@Autowired
	private SenderService sender;
	
	@Test
	@DisplayName("Validate Email 1")
	void testCheckEmail1() {

		MailServiceImpl otpService=new MailServiceImpl();
		   boolean expected=true;
		   boolean actual=otpService.validEmail("abc@gmail.com");
		   assertEquals(expected,actual);
		   
	}

	@Test
	@DisplayName("Validate Email 2")
	void testCheckEmail2() {
		MailServiceImpl otpService=new MailServiceImpl();
	   boolean expected=false;
	   boolean actual=otpService.validEmail("abc@gmail.");
	   assertEquals(expected,actual);
	   
	}
	
	@Test
	@DisplayName("test send otp with wrong email pattern ")
	void testsendOTP2() {
		User otp=new User("muskan");
		assertThrows(Exception.class,()->{service.addUser(otp);});
	}
	

	@Test
	@DisplayName("Test validate otp with email not found")
	void testvalidateOtp1() {
		User otp=new User("muskan@gmail.com");
		assertThrows(Exception.class, ()->{service.validateOtp(otp);});
		
	}
	
	
	@Test
	@DisplayName("test validate otp with wrong email pattern")
	void testvalidateOtp2() {
		User otp=new User("muskan@.");
		assertThrows(Exception.class,()->{service.validateOtp(otp);});
	}
	
	@Test
	@DisplayName("test validate otp with invalid otp")
	void testvalidateOtp3() {
		User otp=new User();
		otp.setEmail("muskan@gmail.com");
		otp.setOtp(123323);
		assertThrows(Exception.class,()->{service.validateOtp(otp);});
	}
	
	@Test
	@DisplayName("test validate otp with otp length")
	void testvalidateOtp4() {
		User otp=new User();
		otp.setEmail("muskan@gmail.com");
		otp.setOtp(1233);
		assertThrows(Exception.class,()->{service.validateOtp(otp);});
	}

	@Test
	@DisplayName("test resend otp")
	void testresendOtp() {
		User otp=new User("muskan");
		assertThrows(Exception.class,()->{service.resendOtp(otp);});
	}
	
	@Test
	@DisplayName("test resend otp with wrong email pattern")
	void testresendOtp2() {
		User otp=new User("muskan@gmail.com");
		assertThrows(Exception.class,()->{service.resendOtp(otp);});
	}
	
	@Test
	void testAddUser() {
		MailServiceImpl otpService=new MailServiceImpl();
		   String expected="Otp sent";
		   User u=new User();
		   u.setEmail("abc@gmail.com");
		   
		   String actual=otpService.addUser(u);
		   System.out.println("Actual result "+actual);
		   assertEquals(expected,actual);
	}

	@Test
	void testvalidate() {
		MailServiceImpl otpService=new MailServiceImpl();
		   String expected="Incorrect otp..try again";
		   User u=new User();
		   u.setEmail("abc@gmail.com");
		   u.setOtp(123456);
		   u.setOtpRequestTime();
		   String actual=otpService.validateOtp(u);
		   assertEquals(expected,actual);
	}
	
	
	@Test
	void testsendotp() {
		
		User u=new User("muskan.1822cs1167@kiet.edu");
		String result=sender.sendEmail(u.getEmail(), "123442", "Your otp");
		String expected="Send mail";
		assertEquals(result,expected);
		
	}
	

	@Test
	void testOtp() {
		User u=new User("muskan@gmail.com");
		MailServiceImpl otpService=new MailServiceImpl();
		otpService.addUser(u);
		int expected=123456;
		int actual=u.getOtp();
		assertNotEquals(expected,actual);
	}
	

}

