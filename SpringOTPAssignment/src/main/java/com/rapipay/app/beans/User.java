package com.rapipay.app.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="otp")
	private int otp;
	
	@Column(name="otpRequestTime")
	private long otpRequestTime;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getOtpRequestTime() {
		return otpRequestTime;
	}

	public void setOtpRequestTime() {
		long date=System.currentTimeMillis()+1000;
		this.otpRequestTime = date;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
		
	
}
