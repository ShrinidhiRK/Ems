package com.tcs.ems.exception;

public class OtpAlreadyVerified extends RuntimeException{
	public OtpAlreadyVerified(String message) {
		super(message);
	}
}
