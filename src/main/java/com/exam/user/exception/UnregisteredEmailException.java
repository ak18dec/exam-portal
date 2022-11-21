package com.exam.user.exception;

public class UnregisteredEmailException extends RuntimeException {

    public UnregisteredEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnregisteredEmailException(String message) {
        super(message);
    }
}
