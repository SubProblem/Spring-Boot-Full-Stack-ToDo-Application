package com.project.TodoList.controller;

import com.project.TodoList.dto.TaskRequestDto;
import com.project.TodoList.dto.TaskResponseDto;
import com.project.TodoList.dto.UserResponseDto;
import com.project.TodoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponseDto getUserInfo(Authentication authentication) {
        return userService.getUserInfo(authentication);
    }


   // @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskRequestDto task, Authentication authentication) throws IOException {

        return userService.addTask(authentication, task);
    }
}
