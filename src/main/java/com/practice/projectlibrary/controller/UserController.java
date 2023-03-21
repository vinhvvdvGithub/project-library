package com.practice.projectlibrary.controller;

import com.practice.projectlibrary.dto.UserDTO;
import com.practice.projectlibrary.dto.request.UserRequest;
import com.practice.projectlibrary.dto.respone.UserRespone;
import com.practice.projectlibrary.entity.User;
import com.practice.projectlibrary.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserRequest userRequest){
        return userService.regisger(userRequest);
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserRequest userRequest){
        return userService.regisger(userRequest);
    }

//    @GetMapping("")
//    public List<UserRespone> users(){
//
//        return userService.users();
//    }
//
//
//    @PostMapping("/add-user")
//    public UserRespone addUser(@RequestBody UserRequest userRequest){
//        return userService.addUser(userRequest);
//    }
//
//    @GetMapping("/find/{email}")
//    @ResponseStatus(HttpStatus.OK)
//    public User findByUserByEmail(@PathVariable("email") @Email(message = "{student.email.notValid}") String email){
//        Optional<User> user =userService.findByEmail(email);
//        return user.get();
//    }
//
//
//    @DeleteMapping("/delete/{email}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteUserByEmail(@PathVariable("email") @Email(message = "{student.email.notValid}") String email){
////    public void deleteUserByEmail(@PathVariable("email") String email){
//        userService.deleteUserByEmail(email);
//    }

}
