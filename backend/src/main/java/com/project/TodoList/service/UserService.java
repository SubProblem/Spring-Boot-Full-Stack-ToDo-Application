package com.project.TodoList.service;

import com.project.TodoList.dto.TaskRequestDto;
import com.project.TodoList.dto.TaskResponseDto;
import com.project.TodoList.dto.UserResponseDto;
import com.project.TodoList.entity.Task;
import com.project.TodoList.entity.User;
import com.project.TodoList.repository.TaskRepository;
import com.project.TodoList.repository.UserRepository;
import com.project.TodoList.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final TaskRepository taskRepository;

    public UserResponseDto getUserInfo(Authentication authentication) {

        var user = (User) authentication.getPrincipal();

        userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new DataIntegrityViolationException("User does not exist"));


        return mapper.UserToDto(user);
    }

    public ResponseEntity<?> addTask(Authentication authentication, TaskRequestDto task) throws IOException {

        var user = (User) authentication.getPrincipal();

        userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new DataIntegrityViolationException("User does not exist"));

        var newTask = Task.builder()
                .task(task.task().getBytes())
                .creationDate(LocalDate.now())
                .user(user)
                .build();

        user.getTasks().add(newTask);

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public UserResponseDto getAllTask(Authentication authentication) {

        var user = (User) authentication.getPrincipal();

        userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new DataIntegrityViolationException("User does not exist"));

        return new UserResponseDto(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                mapper(user.getTasks())
        );

    }

    private TaskResponseDto convert(Task task) {
        return new TaskResponseDto(
                new String(task.getTask(), StandardCharsets.UTF_8),
                task.getCreationDate()
        );
    }

    private List<TaskResponseDto> mapper(List<Task> tasks) {
        return tasks.stream()
                .map(this::convert)
                .toList();
    }
}
