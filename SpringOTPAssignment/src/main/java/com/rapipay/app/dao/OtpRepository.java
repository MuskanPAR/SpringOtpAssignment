package com.rapipay.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapipay.app.beans.User;

public interface OtpRepository extends JpaRepository<User, String> {
	
}
