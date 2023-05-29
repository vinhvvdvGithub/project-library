package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.CategoryRequest;
import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.service.InitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/init")
@RequiredArgsConstructor
public class InitProjectController {
  private final InitService initService;

  @PostMapping("/")
  public void initDatabase(@RequestPart("categoryRequest") List<CategoryRequest> categoryRequest, @RequestPart("listRoleRequest") List<RoleRequest> roleRequestList) {
    initService.initProjectDatabase(categoryRequest, roleRequestList);
  }
}
