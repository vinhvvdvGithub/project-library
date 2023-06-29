package com.practice.projectlibrary.service;

import com.practice.projectlibrary.entity.ConfirmationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IConfirmationTokenService {
    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);

    void saveConfirmationToken(ConfirmationToken token);

    String createToken();

    void userVerifyToken(String token);

    void deleteTokenVerified(String token);


}
