package com.project.TodoList.controller;

import com.project.TodoList.dto.AuthenticationRequest;
import com.project.TodoList.dto.AuthenticationResponse;
import com.project.TodoList.dto.RegisterRequest;
import com.project.TodoList.service.AuthenticationService;
import com.project.TodoList.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RegisterService registerService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return registerService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
