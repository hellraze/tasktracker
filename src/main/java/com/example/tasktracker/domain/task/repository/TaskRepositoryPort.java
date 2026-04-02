package com.example.tasktracker.domain.task.repository;

import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    public Page<Task> getTasks(Pageable pageable) ;
    Optional<Task> findById(UUID id);
    Task save(Task task);
    UUID assign(Task task, User user);
}
