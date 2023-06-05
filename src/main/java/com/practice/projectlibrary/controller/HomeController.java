package com.practice.projectlibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
  @GetMapping("")
  public String homeController(){
    return "<h1>Welcome mini-app</h1>";
  }

}
