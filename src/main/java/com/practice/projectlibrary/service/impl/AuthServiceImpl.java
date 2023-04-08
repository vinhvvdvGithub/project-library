package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.UserExistException;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.security.jwt.JwtService;
import com.practice.projectlibrary.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO regisger(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsUserByEmail(registerRequest.getEmail()) || userRepository.existsUserByUsername(registerRequest.getUsername())) {
            throw new UserExistException("User exist");
        } else {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            Set<Role> role = roleRepository.getRoleBySlug("member");

            user.setRoles(role);
            user.setAvatar("");
            user.setActive(true);
            user.setUpdatedBy("Librarian");
            userRepository.save(user);

            return UserMapper.getInstance().toDTO(user);
        }

    }

    @Override
    public ResponseEntity<RefreshTokenRespone> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmailOrUsername(), loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> userExist = userRepository.getUserByUsernameAndEmail(loginRequest.getEmailOrUsername());

        UserDetails userLogged = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String jwt = jwtService.generateToken(userLogged);
        String refreshToken = refreshTokenService.generateRefreshToken(userExist.get().getId(), userLogged.getUsername()).getRefreshToken();
        return ResponseEntity.ok().body(new RefreshTokenRespone(jwt, refreshToken, "Bearer"));

    }


}
