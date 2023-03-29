package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.service.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class RefreshTokenController {

  private final IRefreshTokenService refreshTokenService;


  @PostMapping("/new-accesstoken")
  public RefreshTokenRespone newAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    return refreshTokenService.generateAccessToken(refreshTokenRequest);
  }


}
