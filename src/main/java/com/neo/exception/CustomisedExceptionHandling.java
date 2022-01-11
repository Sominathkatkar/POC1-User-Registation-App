package com.neo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neo.entity.User;

@RestController
@ControllerAdvice
public class CustomisedExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleExceptions( UserNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(" User Not found");
        ResponseEntity<ExceptionResponse> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
}
