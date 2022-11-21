package com.exam.otp.exception;

public class InvalidOTPException extends RuntimeException{

    public InvalidOTPException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOTPException(String message) {
        super(message);
    }
}
