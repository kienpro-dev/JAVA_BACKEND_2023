package com.example.btvn_buoi45.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleErrorNotFound(NotFoundException e, WebRequest re) {
        return new ErrorResponse(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(InternalServerException.class)
    public ErrorResponse handleErrorInternalServer(InternalServerException e, WebRequest re){
        return new ErrorResponse(e.getMessage(), e.getStatus());
    }
}
