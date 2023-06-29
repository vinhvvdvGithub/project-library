package com.practice.projectlibrary.exception;

public class VerifyToken extends RuntimeException{
    public VerifyToken(String message){
        super(message);
    }
}
