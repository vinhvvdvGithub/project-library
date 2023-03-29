package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;

import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import org.springframework.http.ResponseEntity;


public interface IAuthService {

    ResponseEntity<String> signin(LoginRequest loginRequest);

    //login
    UserDTO login(LoginRequest loginRequest);

    //register
    UserDTO regisger(RegisterRequest registerRequest);


    //login
    ResponseEntity<RefreshTokenRespone> loginJWT(LoginRequest loginRequest);

    //register
    ResponseEntity<String> regisgerJWT(RegisterRequest registerRequest);
}
