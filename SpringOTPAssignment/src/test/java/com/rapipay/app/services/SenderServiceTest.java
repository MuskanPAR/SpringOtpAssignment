package com.rapipay.app.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class SenderServiceTest {

	@Autowired
	private SenderService service;
	
	@Test
	void test() {
		//fail("Not yet implemented");
	}
	

	@Test
	public void testSendemail() {
	
		String actual=service.sendEmail("muskan.1822cs1167@kiet.edu", "123333", "Your otp");
		String expected="Send mail";
		assertEquals(actual,expected);
		
	}

}
