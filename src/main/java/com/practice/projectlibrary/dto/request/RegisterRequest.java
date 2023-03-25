package com.practice.projectlibrary.dto.request;

import lombok.*;

import jakarta.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Username  is mandatory")
    @NonNull
    @NotBlank
    private String username;
    @NotEmpty(message = "Email  is mandatory")
    @NonNull
    @NotBlank
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NonNull
    @NotBlank
    private String password;
}
