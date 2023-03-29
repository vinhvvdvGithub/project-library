package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.entity.RefreshToken;

public class RefreshTokenMapper {
    private static RefreshTokenMapper INSTANCE;

    public static RefreshTokenMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RefreshTokenMapper();
        }
        return INSTANCE;
    }

    //to dto(respone)
    public RefreshTokenRespone toRefreshTokenRespone(RefreshToken refreshToken){
        RefreshTokenRespone refreshTokenRespone = new RefreshTokenRespone();
        refreshTokenRespone.setRefreshToken(refreshToken.getRefreshToken());
        refreshTokenRespone.setAccessToken(refreshTokenRespone.getAccessToken());
        refreshTokenRespone.setType(refreshTokenRespone.getType());
        return refreshTokenRespone;
    }
}
