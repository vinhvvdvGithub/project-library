package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.service.IAuthService;
import com.practice.projectlibrary.service.impl.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody @Valid RegisterRequest RegisterRequest) {
        return authService.regisger(RegisterRequest);
    }


    @PostMapping("/login")
    public ResponseEntity<RefreshTokenRespone> loginJWT(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


    //refresh token route
    @PostMapping("/new-accesstoken")
    @ResponseStatus(HttpStatus.OK)
    public RefreshTokenRespone newAccessToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.generateAccessToken(refreshTokenRequest);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteRefreshToken(@Valid @PathVariable(value = "userId", required = true) Long userId) {
        return refreshTokenService.deteleByUserId(userId);
    }

}
