package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
  Optional<RefreshToken> findByRefreshToken(String refreshToken);

  RefreshTokenRespone generateAccessToken(RefreshTokenRequest refreshTokenRequest);

  RefreshTokenRespone generateRefreshToken(Long userId,String userNameOrEmail);

  RefreshToken verifyExpiration(RefreshToken token);

  int deteleByUserId(Long userId);


}
