package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.RoleDTO;
import com.practice.projectlibrary.dto.request.RoleRequest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IRoleService {

    List<RoleDTO> roles();

    List<RoleDTO> roleDetail(String slug);

    RoleDTO addRole(RoleRequest roleRequest);

    RoleDTO updateRole(Long id,RoleRequest roleRequest);

    RoleDTO deleteRole(Long id);



}
