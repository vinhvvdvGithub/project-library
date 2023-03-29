package com.practice.projectlibrary.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRespone {
    private String accessToken;
    private String refreshToken;
    private String type="Bearer";
}
