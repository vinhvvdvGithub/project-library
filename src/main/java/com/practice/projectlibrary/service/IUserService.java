package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.UserRespone;
import com.practice.projectlibrary.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> users();

    UserDTO addUser(UserRequest userRequest);

    UserDTO findByEmail(String email);

    void deleteUserByEmail(String email);


}
