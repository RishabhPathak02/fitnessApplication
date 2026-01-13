package com.project.fitness.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/hello")
    public String getMessage(){
        return "hello sir" ;
    }
}
