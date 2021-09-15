package com.exam.login.exception;

public class InvalidCredentialsException extends Exception{

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
