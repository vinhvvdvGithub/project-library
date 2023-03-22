package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {

    ResponseEntity<String> signin(LoginRequest loginRequest);

    UserDTO login(LoginRequest loginRequest);

    //register
    UserDTO regisger(RegisterRequest registerRequest);
}
