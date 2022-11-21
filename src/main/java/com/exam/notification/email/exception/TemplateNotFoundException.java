package com.exam.notification.email.exception;

public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateNotFoundException(String message) {
        super(message);
    }
}
