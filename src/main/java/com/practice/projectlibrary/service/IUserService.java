package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.UserRespone;
import com.practice.projectlibrary.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserRespone> users();

    UserRespone addUser(UserRequest userRequest);

    Optional<User> findByEmail(String email);

    void deleteUserByEmail(String email);

    public Integer saveUser(User user);
}
