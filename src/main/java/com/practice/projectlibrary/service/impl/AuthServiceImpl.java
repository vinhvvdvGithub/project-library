package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoginRequest;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.RefreshTokenRespone;
import com.practice.projectlibrary.entity.MyUserDetail;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.security.jwt.JwtService;
import com.practice.projectlibrary.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

        UserDetails userLogged = (UserDetails) userRepository.getUserByUsernameEndEmail(loginRequest.getEmailOrUsername());
//        Set<Role> roles = userLogged.getAuthorities();

        if (authentication != null){
            String jwt = jwtService.generateToken(userLogged);
        }

        return UserMapper.getInstance().toDTO(userExist);
    }


    @Override
    public UserDTO regisger(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        //set role member
//        Set<Role> role = roleRepository.getRoleByRoleId(1);
        Set<Role> role = roleRepository.getRoleBySlug("member");

        user.setRoles(role);
        user.setAvatar("");
        user.setActive(true);
        user.setUpdatedBy("Librarian");
        userRepository.save(user);

        return UserMapper.getInstance().toDTO(user);
    }

    @Override
    public ResponseEntity<RefreshTokenRespone> loginJWT(LoginRequest loginRequest) {
        User userExist = userRepository.getUserByUsernameEndEmail(loginRequest.getEmailOrUsername());


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmailOrUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        UserDetails userLogged = (UserDetails) userRepository.getUserByUsernameEndEmail(loginRequest.getEmailOrUsername());
//        Set<Role> roles = userLogged.getAuthorities();

        UserDetails userLogged = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (authentication != null){
            String jwt = jwtService.generateToken(userLogged);
            String refreshToken = refreshTokenService.generateRefreshToken(userExist.getId()).getRefreshToken();
            return ResponseEntity.ok().body(new RefreshTokenRespone(jwt,refreshToken,"Bearer"));
        }

        return ResponseEntity.ok().body(new RefreshTokenRespone("Error!!","Error!!","Bearer"));
    }

    @Override
    public ResponseEntity<String> regisgerJWT(RegisterRequest registerRequest) {
        return null;
    }

}
