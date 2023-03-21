package com.practice.projectlibrary.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
