package com.exam.quiz.exception;

public class QuizNotFoundException extends Exception {
    public QuizNotFoundException(String message){
        super(message);
    }
    public QuizNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
