package com.practice.projectlibrary.dto.respone;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRespone {
    private String accessToken;
    private String type ="Bearer";
    private String refreshToken;

    private String email;


}