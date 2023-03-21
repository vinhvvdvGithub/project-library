package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.LoginRequest;

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


}
