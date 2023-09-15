package com.socialmediaapp.backendrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NormalController {
    @GetMapping("/home")
    public String getHome(){
        return "home";
    }
}
