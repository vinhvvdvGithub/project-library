package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {

  List<RoleResponse> roles();

  List<RoleResponse> roleDetail(String slug);

  RoleResponse addRole(RoleRequest roleRequest);

  RoleResponse updateRole(Long id, RoleRequest roleRequest);

  RoleResponse deleteRole(Long id);


}
