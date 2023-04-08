package com.practice.projectlibrary.repository;

import com.practice.projectlibrary.entity.RefreshToken;
import com.practice.projectlibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Modifying
    int deleteByUser(User user);

//    @Query(value = "Select * from user where ",nativeQuery = true)
    User findUserByRefreshToken(String refreshToken);

}
