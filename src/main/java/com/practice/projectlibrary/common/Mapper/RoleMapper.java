package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.common.stringUltils.StringConvertToSlug;
import com.practice.projectlibrary.dto.RoleDTO;
import com.practice.projectlibrary.dto.request.RoleRequest;
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
    public Role toEntity(RoleRequest roleRequest){
        Role role = new Role();
        role.setRoleName(roleRequest.getRoleName());
        return role;
    }

    //to entity from dto
    public Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }

    //to dto from entity
    public RoleDTO toDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setCreatedAt(role.getCreatedAt());
        roleDTO.setCreatedBy(role.getCreatedBy());
        return roleDTO;
    }
}
