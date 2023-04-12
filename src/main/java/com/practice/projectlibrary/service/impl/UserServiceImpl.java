package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;

import com.practice.projectlibrary.dto.request.UserRequest;

import com.practice.projectlibrary.dto.response.UserResponse;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.IUserService;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponse> users() {

        return userRepository.findAll().stream().map(
                user -> UserMapper.getInstance().toResponse(user)
        ).collect(Collectors.toList());

    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setAvatar("");
        user.setActive(true);
        user.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

        Set<Role> role = roleRepository.getRoleByRoleId(userRequest.getRoleId());
        user.setRoles(role);

        userRepository.save(user);

        return UserMapper.getInstance().toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Not found user by id");
        }
        return UserMapper.getInstance().toResponse(user.get());
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Not found user by id");
        } else {
            user.get().setActive(false);
            user.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            user.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }


}
