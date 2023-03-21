package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.UserRespone;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.IUserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserRespone> users() {

        return userRepository.findAll().stream().map(
                user -> new UserRespone(user.getEmail(), user.getUsername(), user.getCreatedAt())
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
        if (user.isEmpty()) {
            throw new RuntimeException("Not found user by id");
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

    @Override
    public UserDTO login(LoginRequest loginRequest) {
        Optional<User> userExist = userRepository.findUserByEmail(loginRequest.getEmail());

        if(userExist.isPresent()){
            UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();

        }

        return null;
    }

    @Override
    public UserDTO regisger(UserRequest userRequest) {
        User user = new User();
        user = UserMapper.getInstance().toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(roleRepository.getRoleByRoleId(userRequest.getRoleId()));
        user.setAvatar("");
        user.setActive(true);
        user.setUpdatedBy("Librian");
        userRepository.save(user);

        return UserMapper.getInstance().toDTO(user);
    }


}
