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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public List<UserDTO> users() {

        return userRepository.findAll().stream().map(
                user -> UserMapper.getInstance().toDTO(user)
        ).collect(Collectors.toList());

    }

    @Override
    public UserDTO addUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEmail(userRequest.getEmail());
        user.setAvatar("");
        user.setActive(true);
        user.setCreatedBy("Librian");

        Set<Role> role = roleRepository.getRoleByRoleId(userRequest.getRoleId());
        user.setRoles(role);

        userRepository.save(user);

        return UserMapper.getInstance().toDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Not found user by id");
        }
        return UserMapper.getInstance().toDTO(user.get());
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException("Not found user by id");
        } else {
            user.get().setActive(false);
        }
    }


}
