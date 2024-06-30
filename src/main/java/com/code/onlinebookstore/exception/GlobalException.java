package com.impetus.onlinebookstore.exception;

import com.impetus.onlinebookstore.apiresponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(InvalidPasswordException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(InvalidUserNameException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(BookNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(NoUserPresentException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(NoUserPresentException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(AlreadyBookPresentException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(AlreadyBookPresentException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(NoBookPresentException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(NoBookPresentException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(NoBookSelectedException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(NoBookSelectedException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @ExceptionHandler(NoSuchBookAvailableException.class)
    public ResponseEntity<ApiResponse> invalidPasswordException(NoSuchBookAvailableException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}



//package com.impetus.onlinebookstore.exception;
//
//import com.impetus.onlinebookstore.apiresponse.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalException {
//    @ExceptionHandler(InvalidPasswordException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(InvalidPasswordException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
//    }
//
//    @ExceptionHandler(InvalidUserNameException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(InvalidUserNameException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
//    }
//
//    @ExceptionHandler(BookNotFoundException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(BookNotFoundException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NoUserPresentException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(NoUserPresentException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(AlreadyBookPresentException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(AlreadyBookPresentException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.ALREADY_REPORTED);
//    }
//
//    @ExceptionHandler(NoBookPresentException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(NoBookPresentException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(NoBookSelectedException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(NoBookSelectedException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NO_CONTENT);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }
//
//    @ExceptionHandler(NoSuchBookAvailableException.class)
//    public ResponseEntity<ApiResponse> invalidPasswordException(NoSuchBookAvailableException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
//    }
//}
