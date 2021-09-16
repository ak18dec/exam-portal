package com.exam.subject.exception;

public class SubjectNotFoundException extends Exception {

    public SubjectNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
