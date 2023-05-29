package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.dto.response.RoleResponse;
import com.practice.projectlibrary.entity.Role;

public class RoleMapper {

  private static RoleMapper INSTANCE;

  public static RoleMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new RoleMapper();
    }
    return INSTANCE;
  }

  //to entity from request
  public Role toEntity(RoleRequest roleRequest) {
    Role role = new Role();
    role.setRoleName(roleRequest.getRoleName());
    return role;
  }

  //to response from entity
  public RoleResponse toResponse(Role role) {
    RoleResponse roleResponse = new RoleResponse();
    roleResponse.setRoleName(role.getRoleName());
    roleResponse.setCreatedDate(role.getCreatedDate());
    roleResponse.setCreatedBy(role.getCreatedBy());
    return roleResponse;
  }
}
