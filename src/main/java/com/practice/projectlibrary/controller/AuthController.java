package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;

import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequest loginRequest) {
        return authService.signin(loginRequest);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody RegisterRequest RegisterRequest){
        return authService.regisger(RegisterRequest);
    }


    @PostMapping("/login-jwt")
    public ResponseEntity<RefreshTokenRespone> loginJWT(@RequestBody LoginRequest loginRequest){
        return authService.loginJWT(loginRequest);
    }


}
