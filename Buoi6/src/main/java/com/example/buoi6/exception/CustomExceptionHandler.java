package com.example.buoi6.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorRespone handlerNotFound(NotFoundException e) {
        return new ErrorRespone(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(InternalServerException.class)
    public ErrorRespone handlerInternalServer(InternalServerException e) { return new ErrorRespone(e.getMessage(), e.getStatus()); }
}
