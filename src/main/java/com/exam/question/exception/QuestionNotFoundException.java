package com.exam.question.exception;

public class QuestionNotFoundException extends Exception {

    public QuestionNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public QuestionNotFoundException(String message){
        super(message);
    }
}
