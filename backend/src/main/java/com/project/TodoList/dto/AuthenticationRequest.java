package com.project.TodoList.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
