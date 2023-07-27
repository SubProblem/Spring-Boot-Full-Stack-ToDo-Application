package com.project.TodoList.dto;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
