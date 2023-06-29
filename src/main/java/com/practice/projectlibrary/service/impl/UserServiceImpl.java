package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.common.Mapper.UserMapper;
import com.practice.projectlibrary.dto.request.RegisterRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.response.UserResponse;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IRoleRepository;
import com.practice.projectlibrary.repository.IUserRepository;
import com.practice.projectlibrary.service.IUserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
  private final IUserRepository userRepository;
  private final IRoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private JavaMailSender javaMailSender;


  //testing
  public void register(RegisterRequest registerRequest, String siteURL){
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

    Set<Role> role = roleRepository.getRoleBySlug("member");

    String randomCode = RandomStringUtils.random(64);
    user.setVerificationCode(randomCode);

    user.setRoles(role);
    user.setAvatar("");
    user.setActive(false);
    user.setUpdatedBy("");
    userRepository.save(user);
  }

  private void sendVerificationEmail(RegisterRequest registerRequest, String siteURL) throws MessagingException, UnsupportedEncodingException {

    String toAddress = registerRequest.getEmail();
    String fromAddress = "Your email address";
    String senderName = "Library";
    String subject = "Please verify your registration";
    String content = "Dear [[name]],<br>"
            + "Please click the link below to verify your registration:<br>"
            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
            + "Thank you,<br>"
            + "Your company name.";

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);

    helper.setFrom(fromAddress, senderName);
    helper.setTo(toAddress);
    helper.setSubject(subject);

    content = content.replace("[[name]]", registerRequest.getUsername());
//    String verifyURL = siteURL + "/verify?code=" + registerRequest();

//    content = content.replace("[[URL]]", verifyURL);

    helper.setText(content, true);

    javaMailSender.send(message);
  }


  @Override
  public List<UserResponse> users() {

    return userRepository.findAll().stream().map(
        user -> UserMapper.getInstance().toResponse(user)
    ).collect(Collectors.toList());

  }

  @Override
  public UserResponse addUser(UserRequest userRequest) {
    User user = new User();
    user.setUsername(userRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setEmail(userRequest.getEmail());
    user.setAvatar("");
    user.setActive(true);
    user.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());

    Set<Role> role = roleRepository.getRoleByRoleId(userRequest.getRoleId());
    user.setRoles(role);

    userRepository.save(user);

    return UserMapper.getInstance().toResponse(user);
  }

  @Override
  public UserResponse findByEmail(String email) {
    Optional<User> user = userRepository.findUserByEmail(email);
    if (user.isEmpty()) {
      throw new RuntimeException("Not found user by id");
    }
    return UserMapper.getInstance().toResponse(user.get());
  }

  @Modifying
  public void deleteUserByEmail(String email) {
    Optional<User> user = userRepository.findUserByEmail(email);
    if (user.isEmpty()) {
      throw new RuntimeException("Not found user by id");
    } else {
      user.get().setActive(false);
      user.get().setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
    }
  }


}
