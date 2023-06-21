package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.RefreshTokenMapper;
import com.practice.projectlibrary.dto.request.RefreshTokenRequest;
import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.entity.RefreshToken;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.TokenRefreshException;
import com.practice.projectlibrary.repository.IRefreshTokenRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.security.jwt.JwtService;
import com.practice.projectlibrary.service.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {


  @Value("${security.jwt.token.jwtRefreshExpirationMs}")
  private Long jwtRefreshExpirationMs;


  private final IRefreshTokenRepository refreshTokenRepository;

  private final IUserRepository userRepository;


  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;


  @Override
  public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
    return refreshTokenRepository.findByRefreshToken(refreshToken);
  }


  //generate access token from refresh token
  @Override
  public RefreshTokenResponse generateAccessToken(RefreshTokenRequest refreshTokenRequest) {
    RefreshTokenResponse refreshTokenRespone = new RefreshTokenResponse();

    Optional<RefreshToken> refreshTokenExist = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken());
    if (refreshTokenExist.isPresent()) {
      RefreshToken oldRefreshToken = this.verifyExpiration(refreshTokenExist.get());
      User oldRefreshTokenUser = oldRefreshToken.getUser();

      String accessToken = jwtService.generateToken(oldRefreshTokenUser.getEmail());

      refreshTokenRespone.setRefreshToken(refreshTokenRequest.getRefreshToken());
      refreshTokenRespone.setAccessToken(accessToken);
      refreshTokenRespone.setType("Bearer");

      return refreshTokenRespone;
    } else {
      throw new TokenRefreshException(refreshTokenRequest.getRefreshToken(), "Refresh token not found");

    }

  }

  //generate refresh token for user
  @Override
  public RefreshTokenResponse generateRefreshToken(Long userId, String userNameOrEmail) {
    RefreshToken token = new RefreshToken();
    token.setUser(userRepository.getUserByUsernameAndEmail(userNameOrEmail).get());
    token.setRefreshToken(UUID.randomUUID().toString());
    token.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
    refreshTokenRepository.save(token);
    return RefreshTokenMapper.getInstance().toRefreshTokenRespone(token);
  }


  //check time expiry refresh token
  @Override
  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getRefreshToken(), "Refresh token was expired. Please make a new signin request");
    }
    return token;
  }

  @Override
  public int deteleByUserId(Long userId) {

    return refreshTokenRepository.deleteByUser(userRepository.findUserById(userId).get());
  }
}
