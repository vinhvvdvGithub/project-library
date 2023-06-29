package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {


    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);
}
