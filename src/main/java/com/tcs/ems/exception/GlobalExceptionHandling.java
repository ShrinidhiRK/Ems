package com.tcs.ems.exception;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> invalidData(MethodArgumentNotValidException exception){
	    //String message=exception.getBindingResult().getFieldError().getDefaultMessage(); 
	    //return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		
		Map<String, String> message=new HashMap<String,String>();
		List<FieldError> errors=exception.getBindingResult().getFieldErrors();
		for(FieldError fe:errors) {
			message.put(fe.getField(), fe.getDefaultMessage());
		}
		return new ResponseEntity<Map<String,String>>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException userNotFoundException){
		return new ResponseEntity<String>(userNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidOtpException.class)
	public ResponseEntity<String> invalidOtp(InvalidOtpException invalidOtpException){
		return new ResponseEntity<String>(invalidOtpException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OtpExpireException.class)
	public ResponseEntity<String> otpExpiry(OtpExpireException otpExpireException){
		return new ResponseEntity<String>(otpExpireException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OtpSuccessfull.class)
	public ResponseEntity<String> otpsuccess(OtpSuccessfull otpSuccessfull){
		return new ResponseEntity<String>(otpSuccessfull.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(OtpAlreadyVerified.class)
	public ResponseEntity<String> otpAlreadyVerified(OtpAlreadyVerified otpAlreadyVerified){
		return new ResponseEntity<String> (otpAlreadyVerified.getMessage(),HttpStatus.ACCEPTED);
	}
}
