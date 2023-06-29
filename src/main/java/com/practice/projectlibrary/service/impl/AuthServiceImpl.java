package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.response.RefreshTokenResponse;
import com.practice.projectlibrary.dto.response.UserResponse;
import com.practice.projectlibrary.entity.ConfirmationToken;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.exception.UserExistException;
import com.practice.projectlibrary.exception.VerifyToken;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.security.jwt.JwtService;
import com.practice.projectlibrary.service.IAuthService;
import com.practice.projectlibrary.service.IConfirmationTokenService;
import com.practice.projectlibrary.service.IMailService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private final IConfirmationTokenService confirmationTokenService;
    private final IMailService mailService;

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public UserResponse regisger(RegisterRequest registerRequest) {
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
            user.setActive(false);
            user.setUpdatedBy("");
            userRepository.save(user);

            String token = confirmationTokenService.createToken();

            //set confirmation token
            ConfirmationToken confirmationToken = new ConfirmationToken();
            confirmationToken.setToken(token);
            confirmationToken.setCreatedAt(LocalDateTime.now());
            confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            confirmationToken.setConfirmedAt(null);
            confirmationToken.setUser(user);
            confirmationTokenService.saveConfirmationToken(confirmationToken);

            String link = "http://localhost:8080/api/v1/auth/registration/confirm?token=" + token;

            logger.info("Link confirm created: " + link);

            mailService.send(registerRequest.getEmail(),
                    mailService.buildEmail(registerRequest.getUsername(), link));


            logger.info("confirmation token" + confirmationToken.getToken());


            return UserMapper.getInstance().toResponse(user);
        }

    }


    @Override
    public ResponseEntity<RefreshTokenResponse> login(LoginRequest loginRequest) {


        Optional<User> userExist = userRepository.getUserByUsernameAndEmail(loginRequest.getEmailOrUsername());

        if (userExist.isPresent()) {
            if (userExist.get().getActive() == true) {
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
                throw new VerifyToken("This account inactive");
            }

        } else {
            throw new UsernameNotFoundException("User not found");
        }


    }


}
