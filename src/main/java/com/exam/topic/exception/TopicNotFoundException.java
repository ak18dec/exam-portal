package com.exam.topic.exception;

public class TopicNotFoundException extends Exception{

    public TopicNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public TopicNotFoundException(String message){
        super(message);
    }
}
