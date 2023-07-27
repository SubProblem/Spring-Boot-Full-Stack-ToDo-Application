package com.project.TodoList.service;

import com.project.TodoList.dto.RegisterRequest;
import com.project.TodoList.entity.Role;
import com.project.TodoList.entity.User;
import com.project.TodoList.jwt.JwtService;
import com.project.TodoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.project.TodoList.entity.Role.USER;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public ResponseEntity<?> register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(USER)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
