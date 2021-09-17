package com.exam.instruction.exception;

public class InstructionNotFoundException extends Exception {

    public InstructionNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public InstructionNotFoundException(String message) {
        super(message);
    }
}
