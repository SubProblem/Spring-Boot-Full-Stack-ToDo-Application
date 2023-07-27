package com.project.TodoList.utils;

import com.project.TodoList.dto.TaskResponseDto;
import com.project.TodoList.dto.UserResponseDto;
import com.project.TodoList.entity.Task;
import com.project.TodoList.entity.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class Mapper {

    public UserResponseDto UserToDto(User user) {
        return new UserResponseDto(
                user.getUsername(),
                user.getLastname(),
                user.getEmail(),
                mapper(user.getTasks())
        );
    }

    private List<TaskResponseDto> mapper(List<Task> tasks) {
        return tasks.stream()
                .map(this::TaskToDto)
                .toList();
    }

    public TaskResponseDto TaskToDto(Task task) {
        return new TaskResponseDto(
                convert(task.getTask()),
                task.getCreationDate()
        );
    }

    private String convert(byte[] fileBytes) {
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
}
