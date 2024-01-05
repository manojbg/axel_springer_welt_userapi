package com.axelspringer.welt.weltuserapi.controllers;

import com.axelspringer.welt.weltuserapi.dto.UserDetails;
import com.axelspringer.welt.weltuserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public Mono<UserDetails> getUserDetails(@PathVariable Long userId) {
        return userService.getUserDetails(userId);
    }
}