package com.exam.common.exception.handler;

import org.springframework.http.HttpStatus;

public class ErrorMessageEntity {

    private HttpStatus status;
    private String message;

    public ErrorMessageEntity() {
    }

    public ErrorMessageEntity(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessageEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
