package com.tcs.ems.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.dto.VerifyOtpRequest;
import com.tcs.ems.service.OtpService;
import com.tcs.ems.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	private OtpService otpService;
	
	public UserController(UserService userService,OtpService otpService) {
		this.userService = userService;
		this.otpService=otpService;
	}
	
	@PostMapping("/register")
	private String register(@RequestBody RegisterRequest registerRequest) {
		//return "otp sent";
		return userService.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		//return "verified";
		return otpService.VerifyOtp(verifyOtpRequest);
	}
	
}
