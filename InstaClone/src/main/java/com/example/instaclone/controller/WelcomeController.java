package com.example.instaclone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController tells Spring: "methods in this class answer web requests,
// and whatever they return is sent back as the response body."
@RestController
public class WelcomeController {

    // Handles GET http://localhost:<port>/welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to InstaClone!";
    }
}
