package com.exam.topic.exception;

public class TopicAlreadyExistsException extends Exception{

    public TopicAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }

    public TopicAlreadyExistsException(String message){
        super(message);
    }
}
