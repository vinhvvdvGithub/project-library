package com.practice.projectlibrary.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
