package com.practice.projectlibrary.common.Mapper;

import com.practice.projectlibrary.dto.LoanDTO;
import com.practice.projectlibrary.dto.RoleDTO;
import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.LoanRequest;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.entity.Loan;
import com.practice.projectlibrary.entity.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {
    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
    }


    //to Entity from request
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    //to Entity from dto
//    public Loan toEntity(LoanDTO loanDTO) {
//        Loan loan = new Loan();
//        loan.setBookId(loanDTO.getBookId());
//        loan.setQuantity(loanDTO.getQuantity());
//        loan.setActive(loanDTO.getActive());
//        loan.setStatus(loanDTO.getStatus());
//        loan.setDateOfCheckout(loanDTO.getDateOfCheckout());
//        loan.setDataDue(loanDTO.getDataDue());
//        loan.setDateReturned(loanDTO.getDateReturned());
//        return loan;
//
//    }

    //to DTO

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setCreatedBy(user.getCreatedBy());
        userDTO.setRoles(user.getRoles().stream().map(role -> RoleMapper.getInstance().toDTO(role)).collect(Collectors.toSet()));
        return userDTO;
    }

}
