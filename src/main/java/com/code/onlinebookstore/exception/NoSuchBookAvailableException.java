package com.code.onlinebookstore.exception;

public class NoSuchBookAvailableException extends RuntimeException{
    public NoSuchBookAvailableException(String s){
        super(s);
    }
}
