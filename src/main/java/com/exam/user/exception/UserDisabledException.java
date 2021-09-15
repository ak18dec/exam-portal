package com.exam.user.exception;

public class UserDisabledException extends Exception {
    public UserDisabledException(String message) {
        super(message);
    }

    public UserDisabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
