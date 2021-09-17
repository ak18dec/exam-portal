package com.exam.instruction.exception;

public class InstructionAlreadyExistsException extends Exception {

    public InstructionAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }

    public InstructionAlreadyExistsException(String message){
        super(message);
    }
}
