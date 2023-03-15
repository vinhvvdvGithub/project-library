package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.UserRespone;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.IUserService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private IUserRepository userRepository;

    @Override
    public List<UserRespone> users() {

        return userRepository.findAll().stream().map(
                user -> new UserRespone(user.getEmail(),user.getUsername(),user.getCreatedAt())
        ).collect(Collectors.toList());
    }

    @Override
    public UserRespone addUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setActive(true);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);


        UserRespone userRespone = new UserRespone();
        userRespone.setEmail(userRequest.getEmail());
        userRespone.setUsername(userRequest.getUsername());
        return userRespone;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()){
            throw  new RuntimeException("Not found user by id");
        }
        return Optional.of(user.get());
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public Integer saveUser(User user) {
        return null;
    }


}
