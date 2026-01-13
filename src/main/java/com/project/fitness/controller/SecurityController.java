package com.project.fitness.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/hello")
    public String getMessage(){
        return "hello sir" ;
    }
    @GetMapping("/admin/hello")
    public String sayAdminHello(){
        return "Hello, Admin";
    }
    @GetMapping("/user/hello")
    public String sayuserHello(){
        return "Hello, User";
    }
}
