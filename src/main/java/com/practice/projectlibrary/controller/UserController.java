package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.UserRequest;

import com.practice.projectlibrary.service.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;



    @GetMapping("")
    public List<UserDTO> users(){

        return userService.users();
    }

    @PostMapping("/add-user")
    public UserDTO addUser(@RequestBody @Valid UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @GetMapping("/find/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByUserByEmail(@PathVariable("email") @Email(message = "{student.email.notValid}") String email){
        return userService.findByEmail(email);
    }


    @DeleteMapping("/delete/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserByEmail(@PathVariable("email") @Email(message = "{student.email.notValid}") String email){
//    public void deleteUserByEmail(@PathVariable("email") String email){
        userService.deleteUserByEmail(email);
    }

}
