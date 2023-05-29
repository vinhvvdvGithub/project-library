package com.practice.projectlibrary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String email;
  private String username;
  private String avatar;
  private Timestamp createdDate;
  private String createdBy;
  private Set<RoleResponse> roles;
}
