package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


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


  @NotNull(message = "Role is mandatory")
  private Long roleId;
}
