package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.entity.ConfirmationToken;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.IConfirmationTokenRepository;
import com.practice.projectlibrary.service.IConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements IConfirmationTokenService {

    private final IConfirmationTokenRepository confirmationTokenRepository;
    private final Logger logger = LoggerFactory.getLogger(ConfirmationTokenServiceImpl.class);

    @Override
    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByToken(token);
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public String createToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void userVerifyToken(String token) {
        Optional<ConfirmationToken> currentToken = confirmationTokenRepository.findConfirmationTokenByToken(token);
        if (currentToken.isPresent()) {
            if (currentToken.get().getUser().getActive() == true) {
                throw new RuntimeException("This account has already been verified, please! login...");
            } else if (currentToken.get().getExpiresAt().isAfter(LocalDateTime.now())) {
                currentToken.get().setConfirmedAt(LocalDateTime.now());
                currentToken.get().getUser().setActive(true);
                confirmationTokenRepository.save(currentToken.get());
            } else {
                throw new RuntimeException("Token expired");
            }
        } else {
            throw new NotFoundException("Not found Token");
        }


    }

    @Override
    public void deleteTokenVerified(String token) {
        Optional<ConfirmationToken> currentToken = confirmationTokenRepository.findConfirmationTokenByToken(token);

        if(currentToken.isPresent()){
            if(currentToken.get().getConfirmedAt() != null){
                confirmationTokenRepository.delete(currentToken.get());
                logger.info(currentToken.get().getToken()+ "has removed");
            }
        }else {
            throw new NotFoundException("Not found Token");
        }


    }
}
