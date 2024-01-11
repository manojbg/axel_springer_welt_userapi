package com.welt.userapi.controller;

import com.welt.userapi.dto.UserInformation;
import com.welt.userapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Get user details by userId", description = "Get user details and list of all posts assigned to given userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Mono<UserInformation>> getUserInformation(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserInformation(userId));
    }
}