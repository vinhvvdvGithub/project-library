package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {

    ResponseEntity<String> signin(@RequestBody LoginRequest loginRequest);
}
