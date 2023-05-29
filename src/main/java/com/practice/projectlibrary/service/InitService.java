package com.practice.projectlibrary.service;


import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.request.RoleRequest;

import java.util.List;

public interface InitService {

  void initProjectDatabase(List<CategoryRequest> categoryRequest, List<RoleRequest> listRoleRequest);
}
