package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


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
