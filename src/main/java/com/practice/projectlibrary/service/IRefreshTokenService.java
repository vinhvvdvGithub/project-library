package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
  Optional<RefreshToken> findByRefreshToken(String refreshToken);

  RefreshTokenResponse generateAccessToken(RefreshTokenRequest refreshTokenRequest);

  RefreshTokenResponse generateRefreshToken(Long userId, String userNameOrEmail);

  RefreshToken verifyExpiration(RefreshToken token);

  int deteleByUserId(Long userId);


}
