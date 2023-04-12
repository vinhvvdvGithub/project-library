package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;

import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;


public interface IAuthService {


    //register
    UserResponse regisger(RegisterRequest registerRequest);


    //login
    ResponseEntity<RefreshTokenResponse> login(LoginRequest loginRequest);

}
