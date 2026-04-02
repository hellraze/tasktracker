package com.example.tasktracker.domain.user.repository;

import com.example.tasktracker.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findByID(UUID id);
    Optional<User> save(User user);
}
