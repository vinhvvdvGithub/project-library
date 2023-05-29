package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
