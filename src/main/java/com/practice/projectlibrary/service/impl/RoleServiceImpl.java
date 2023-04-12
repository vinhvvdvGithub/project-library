package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.RoleMapper;
import com.practice.projectlibrary.common.stringUltils.StringConvertToSlug;
import com.practice.projectlibrary.dto.request.RoleRequest;
import com.practice.projectlibrary.dto.response.RoleResponse;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.exception.NotFoundException;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<RoleResponse> roles() {
        List<RoleResponse> roleResponses = new ArrayList<>();
        roleRepository.roles().stream().map(
                role -> roleResponses.add(RoleMapper.getInstance().toResponse(role))
        ).collect(Collectors.toList());

        return roleResponses;
    }

    @Override
    public List<RoleResponse> roleDetail(String slug) {
        List<RoleResponse> roleResponses = new ArrayList<>();
        roleRepository.roleDetail(slug).stream().map(
                role -> roleResponses.add(RoleMapper.getInstance().toResponse(role))
        ).collect(Collectors.toList());
        return roleResponses;
    }

    @Override
    public RoleResponse addRole(RoleRequest roleRequest) {
        Role role = RoleMapper.getInstance().toEntity(roleRequest);
        role.setActive(true);
        role.setSlug(StringConvertToSlug.covertStringToSlug(roleRequest.getRoleName()));
        role.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        roleRepository.save(role);
        return RoleMapper.getInstance().toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {

        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            role.get().setRoleName(roleRequest.getRoleName());
            role.get().setActive(true);
            role.get().setSlug(StringConvertToSlug.covertStringToSlug(roleRequest.getRoleName()));
            role.get().setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            roleRepository.save(role.get());
            return RoleMapper.getInstance().toResponse(role.get());

        } else {
            throw new NotFoundException("Role id not found");
        }
    }

    @Override
    public RoleResponse deleteRole(Long id) {
//        Optional<Role> role = roleRepository.getReferenceById(id);
        Role role = roleRepository.getReferenceById(id);

        if (role != null) {
            role.setActive(false);
            roleRepository.save(role);
            return RoleMapper.getInstance().toResponse(role);

        } else {
            throw new NotFoundException("Role id not found");
        }

    }
}
