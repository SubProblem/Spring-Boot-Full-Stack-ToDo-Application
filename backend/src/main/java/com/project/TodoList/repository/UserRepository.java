package com.project.TodoList.repository;

import com.project.TodoList.entity.Task;
import com.project.TodoList.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.tasks t WHERE u.email = :email")
    Optional<User> findAllTasksForUser(@Param("email") String email);

    Optional<User> findByEmail(String email);
}