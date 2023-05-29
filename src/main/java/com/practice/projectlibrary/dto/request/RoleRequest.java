package com.practice.projectlibrary.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

  @NotEmpty(message = "RoleName is mandatory")
  @NonNull
  @NotBlank
  private String roleName;

}
