package com.practice.projectlibrary.dto.request;

import lombok.*;

import jakarta.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "Email or Username is mandatory")
    @NotNull
    @NotBlank
    private String emailOrUsername;

    @NotEmpty(message = "password is mandatory")
    @NotNull
    @NotBlank
    private String password;
}
