package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.response.UserResponse;
import com.practice.projectlibrary.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }


    //to Entity from request
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    //to response
    public UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setAvatar(user.getAvatar());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setCreatedBy(user.getCreatedBy());
        userResponse.setRoles(user.getRoles().stream().map(role -> RoleMapper.getInstance().toResponse(role)).collect(Collectors.toSet()));
        return userResponse;
    }


}
