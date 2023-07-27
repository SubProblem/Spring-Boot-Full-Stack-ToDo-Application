package com.project.TodoList.dto;

import java.util.List;

public record UserResponseDto(
        String username,
        String lastname,
        String email,
        List<TaskResponseDto> tasks
) {
}
