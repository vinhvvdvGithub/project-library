package com.practice.projectlibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {


  //not found exception
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorMessage handlerNotFoundException(NotFoundException ex, WebRequest req) {
    return new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  //bad request exception
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handlerBadRequestException(BadRequestException ex, WebRequest req) {
    return new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadCredentialsException.class)
  public ErrorMessage handleBadCredentialsException(BadCredentialsException ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

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

  @ExceptionHandler(TokenRefreshException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
  }


  @ExceptionHandler(TokenException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleTokenException(Exception ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }


  //exception chua khai bao
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleException(Exception ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  //exception for auth service
  @ExceptionHandler(AuthenticationException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMessage handleAuthenticationException(Exception ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.UNAUTHORIZED, ex.getMessage());
  }

  @ExceptionHandler(UserExistException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleUserExistException(Exception ex, WebRequest web) {
    return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }


}
