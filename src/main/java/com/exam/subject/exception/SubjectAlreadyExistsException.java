package com.exam.subject.exception;

public class SubjectAlreadyExistsException extends Exception {

    public SubjectAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }

    public SubjectAlreadyExistsException(String message) {
        super(message);
    }
}
