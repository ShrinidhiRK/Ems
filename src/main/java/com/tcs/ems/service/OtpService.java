package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.dto.VerifyOtpRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.exception.InvalidOtpException;
import com.tcs.ems.exception.OtpAlreadyVerified;
import com.tcs.ems.exception.OtpExpireException;
import com.tcs.ems.exception.OtpSuccessfull;
import com.tcs.ems.exception.UserNotFoundException;
import com.tcs.ems.repository.UserRepository;

@Service
public class OtpService {
	private UserRepository userRepository;

	public OtpService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User> optionaluser=userRepository.findByEmail(verifyOtpRequest.getEmail());
		if(optionaluser.isPresent()) {
			User user=optionaluser.get();
			if(user.getOtp()==null) {
				throw new OtpAlreadyVerified("Otp Already Verified");
			}
			if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
				throw new InvalidOtpException("invalid otp");
			}
			if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
				throw new OtpExpireException("otp expired");
			}
			else {
				user.setVerified(true);
				user.setOtp(null);
				user.setOtpexpirytime(null);
				userRepository.save(user);
				throw new  OtpSuccessfull("otp verified successfully");
			}
		}
		else {
			throw new UserNotFoundException("user not found");
		}
	}
}
