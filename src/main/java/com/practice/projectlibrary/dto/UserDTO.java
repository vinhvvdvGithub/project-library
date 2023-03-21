package com.practice.projectlibrary.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String username;
    private String avatar;
    private Timestamp createdAt;
    private String createdBy;
    private Set<RoleDTO> roles;
}
