package com.practice.projectlibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler  {

    //not found exception
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundException(NotFoundException ex, WebRequest req){
        return new ErrorMessage(HttpStatus.NOT_FOUND,ex.getMessage());
    }

    //bad request exception
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerBadRequestException(BadRequestException ex, WebRequest req){
        return new ErrorMessage(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

//    //validation exception, haven't finished
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorMessage handlerBindException(BindException ex){
//        String errorMessage = "Request không hợp lệ";
//        if (ex.getBindingResult().hasErrors())
//            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        return new ErrorMessage(HttpStatus.BAD_REQUEST,errorMessage);
//
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
