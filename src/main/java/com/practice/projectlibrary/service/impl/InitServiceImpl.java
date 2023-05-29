package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.service.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitServiceImpl implements InitService {

  private final RoleServiceImpl roleService;

  private final CategoryServiceImpl categoryService;

  @Override
  public void initProjectDatabase(List<CategoryRequest> categoryRequest, List<RoleRequest> listRoleRequest) {
    categoryService.addListCategory(categoryRequest);
    listRoleRequest.stream().map(
        roleRequest -> roleService.addRole(roleRequest)
    ).collect(Collectors.toSet());
  }
}
