package com.exam.question.exception;

public class QuestionAlreadyExistsException extends Exception {

    public QuestionAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }

    public QuestionAlreadyExistsException(String message){
        super(message);
    }
}
