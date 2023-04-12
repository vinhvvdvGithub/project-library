package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.entity.RefreshToken;

public class RefreshTokenMapper {
    private static RefreshTokenMapper INSTANCE;

    public static RefreshTokenMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RefreshTokenMapper();
        }
        return INSTANCE;
    }

    //to refresh token response
    public RefreshTokenResponse toRefreshTokenRespone(RefreshToken refreshToken){
        RefreshTokenResponse refreshTokenRespone = new RefreshTokenResponse();
        refreshTokenRespone.setRefreshToken(refreshToken.getRefreshToken());
        refreshTokenRespone.setAccessToken(refreshTokenRespone.getAccessToken());
        refreshTokenRespone.setType(refreshTokenRespone.getType());
        return refreshTokenRespone;
    }
}
