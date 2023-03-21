package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.RoleDTO;
import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public List<RoleDTO> dtos(){
        return roleService.roles();
    }

    @GetMapping("/search")
    public List<RoleDTO> dtos(@RequestParam("slug") String slug){
        return roleService.roleDetail(slug);
    }

    @PostMapping("/")
    public RoleDTO addRole(@RequestBody RoleRequest roleRequest){
        return roleService.addRole(roleRequest);
    }


    @PutMapping("/{id}")
    public RoleDTO updateRole(@RequestBody RoleRequest roleRequest,@PathVariable("id") Long id){
        return roleService.updateRole(id,roleRequest);
    }

    @DeleteMapping("/{id}")
    public RoleDTO deleteRole(@PathVariable("id") Long id){
        return roleService.deleteRole(id);
    }


}
