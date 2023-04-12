package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.dto.response.RoleResponse;
import com.practice.projectlibrary.service.IRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public List<RoleResponse> dtos(){
        return roleService.roles();
    }

    @GetMapping("/search")
    public List<RoleResponse> dtos(@RequestParam("slug") String slug){
        return roleService.roleDetail(slug);
    }

    @PostMapping("/")
    public RoleResponse addRole(@Valid @RequestBody RoleRequest roleRequest){
        return roleService.addRole(roleRequest);
    }


    @PutMapping("/{id}")
    public RoleResponse updateRole(@Valid @RequestBody RoleRequest roleRequest, @PathVariable("id") Long id){
        return roleService.updateRole(id,roleRequest);
    }

    @DeleteMapping("/{id}")
    public RoleResponse deleteRole(@PathVariable("id") Long id){
        return roleService.deleteRole(id);
    }


}
