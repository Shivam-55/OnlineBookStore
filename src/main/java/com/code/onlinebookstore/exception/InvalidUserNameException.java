package com.code.onlinebookstore.exception;

public class InvalidUserNameException extends RuntimeException{
    public InvalidUserNameException(String s) {
        super(s);
    }
}
