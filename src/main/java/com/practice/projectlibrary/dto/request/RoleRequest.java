package com.practice.projectlibrary.dto.request;

import lombok.*;

import jakarta.validation.constraints.*;


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
