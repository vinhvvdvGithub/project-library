package com.practice.projectlibrary.service.impl;

import com.practice.projectlibrary.entity.MyUserDetail;
import com.practice.projectlibrary.entity.Role;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailSeviceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;


    @Transactional // protect LazyInitializationException
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUsernameAndEmail(usernameOrEmail);


        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Not found user by username or email");
        } else {
            Set<GrantedAuthority> authorities = user.get().getRoles().stream().map(
                    role -> new SimpleGrantedAuthority(role.getRoleName())
            ).collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
        }

    }

}
