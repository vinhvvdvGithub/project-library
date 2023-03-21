package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.entity.MyUserDetail;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailSeviceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

//    public MyUserDetailSeviceImpl(IUserRepository userRepository){
//        this.userRepository=userRepository;
//    }
//

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsernameEndEmail(usernameOrEmail);
//        Optional<User> user = userRepository.findUserByUsernameOrEmailAAndActiveIsTrue(usernameOrEmail, usernameOrEmail);
//        List<SimpleGrantedAuthority> grantedAuthorities = user.().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        if (user == null) {
            throw new UsernameNotFoundException("Not found user by username or email");
        }

//        Set<GrantedAuthority> authorities = user.get().getRoles().stream().map(
//                role -> new SimpleGrantedAuthority(role.getRoleName())
//        ).collect(Collectors.toSet());
        Set<GrantedAuthority> authorities = user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRoleName())
        ).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
