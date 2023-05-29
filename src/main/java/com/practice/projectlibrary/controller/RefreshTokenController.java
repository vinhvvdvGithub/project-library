package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.service.IRefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class RefreshTokenController {

  private final IRefreshTokenService refreshTokenService;


  @PostMapping("/new-accesstoken")
  @ResponseStatus(HttpStatus.OK)
  public RefreshTokenResponse newAccessToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
    return refreshTokenService.generateAccessToken(refreshTokenRequest);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public int deleteRefreshToken(@Valid @PathVariable(value = "userId", required = true) Long userId) {
    return refreshTokenService.deteleByUserId(userId);
  }
}
