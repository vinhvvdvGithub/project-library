package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<String> signin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmailOrUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @Override
    public UserDTO login(LoginRequest loginRequest) {
        User userExist = userRepository.getUserByUsernameEndEmail(loginRequest.getEmailOrUsername());


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmailOrUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return UserMapper.getInstance().toDTO(userExist);
    }


    @Override
    public UserDTO regisger(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        //set role member
        Set<Role> role = roleRepository.getRoleByRoleId(2);

        user.setRoles(role);
        user.setAvatar("");
        user.setActive(true);
        user.setUpdatedBy("Librian");
        userRepository.save(user);

        return UserMapper.getInstance().toDTO(user);
    }

}
