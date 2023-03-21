package com.practice.projectlibrary.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotEmpty(message = "Username is mandatory")
    @NonNull
    @NotBlank
    private String username;

    @NotEmpty(message = "password is mandatory")
    @NotNull
    @NotBlank
    private String password;


    @NotEmpty(message = "Email is mandatory")
    @NotNull
    @NotBlank
    private String email;


    @NotEmpty(message = "Role is mandatory")
    @NotNull
    @NotBlank
    private Integer roleId;
}
