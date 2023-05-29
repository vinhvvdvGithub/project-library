package com.practice.projectlibrary.service;

import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.response.UserResponse;

import java.util.List;

public interface IUserService {
  List<UserResponse> users();

  UserResponse addUser(UserRequest userRequest);

  UserResponse findByEmail(String email);

  void deleteUserByEmail(String email);


}
