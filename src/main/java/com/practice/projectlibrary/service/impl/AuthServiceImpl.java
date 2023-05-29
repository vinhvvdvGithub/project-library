package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.dto.response.UserResponse;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
  public UserResponse regisger(@Valid @RequestBody RegisterRequest registerRequest) {
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
      user.setUpdatedBy("");
      userRepository.save(user);

      return UserMapper.getInstance().toResponse(user);
    }

  }

  @Override
  public ResponseEntity<RefreshTokenResponse> login(LoginRequest loginRequest) {
    Optional<User> userExist = userRepository.getUserByUsernameAndEmail(loginRequest.getEmailOrUsername());

    if (userExist.isPresent()) {
      Authentication authentication;
      try {
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequest.getEmailOrUsername(), loginRequest.getPassword()
        ));
      } catch (Exception e) {
        throw new RuntimeException("Username or password incorrect!");
      }


      SecurityContextHolder.getContext().setAuthentication(authentication);


      UserDetails userLogged = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      String jwt = jwtService.generateToken(userLogged);
      String refreshToken = refreshTokenService.generateRefreshToken(userExist.get().getId(), userLogged.getUsername()).getRefreshToken();
      return ResponseEntity.ok().body(new RefreshTokenResponse(jwt, refreshToken, "Bearer"));
    } else {
      throw new UsernameNotFoundException("User not found");
    }


  }


}
