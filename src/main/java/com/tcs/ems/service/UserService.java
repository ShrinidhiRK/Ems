package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.repository.UserRepository;
import com.tcs.ems.util.OtpGenerator;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private EmailService emailService;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,EmailService emailService,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.emailService=emailService;
		this.passwordEncoder=passwordEncoder;
	}
	
	public String register(RegisterRequest registerRequest) {
		Optional<User> ou=userRepository.findByEmail(registerRequest.getEmail());
		if(ou.isPresent()) {
			return "emailid already exist";
		}
		else {
			User user=new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
			user.setRole("USER_ROLE");
			user.setVerified(false);
			String otp=OtpGenerator.generateOtp();
			user.setOtp(otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(5));
			userRepository.save(user);
			
			emailService.sendOtp(registerRequest.getEmail(), otp);
			return "please check your email for otp";
		}
	}
	
}
