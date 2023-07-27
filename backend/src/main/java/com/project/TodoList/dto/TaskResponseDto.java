package com.project.TodoList.dto;

import java.time.LocalDate;

public record TaskResponseDto(
        String task,
        LocalDate creationDate
) {
}
