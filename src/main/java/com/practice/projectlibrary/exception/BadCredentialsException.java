package com.practice.projectlibrary.exception;

public class BadCredentialsException extends RuntimeException{
  public BadCredentialsException(String message){
    super(message);
  }
}
