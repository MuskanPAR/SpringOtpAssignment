package com.rapipay.app.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rapipay.app.beans.User;
import com.rapipay.app.dao.OtpRepository;
import com.rapipay.app.services.MailService;
import com.rapipay.app.services.MailServiceImpl;

class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private MailServiceImpl service;
	
	   @MockBean
	   private Controller Controller;

	@Test
	void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testgenerate() throws Exception {

		User user=new User();
		user.setEmail("muskan.1822cs1167");
		user.setOtp(122344);
		user.setOtpRequestTime();
		assertThrows(Exception.class,()->{service.addUser(user);});	
		
	}
	
	@Test
	public void testvalidate() throws Exception {

		User user=new User();
		user.setEmail("muskan.1822cs1167");
		user.setOtp(122344);
		assertThrows(Exception.class,()->{service.validateOtp(user);});
		
	}
	
	@Test
	public void testresend() throws Exception{
		User user=new User();
		user.setEmail("muskan.1822cs1167");
		assertThrows(Exception.class,()->{service.resendOtp(user);});
	}
	

	

	@Test
	public void testgenerate1() throws Exception {
		User u=new User();
		u.setEmail("muskan");
		u.setOtp(123);
		assertThrows(Exception.class,()->{service.validateOtp(u);});
	}
	
}
