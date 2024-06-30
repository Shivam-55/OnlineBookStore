package com.code.onlinebookstore.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String s) {
        super(String.format("%s", s));
    }
}
