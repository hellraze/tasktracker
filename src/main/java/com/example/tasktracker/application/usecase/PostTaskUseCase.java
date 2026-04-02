package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.CreateTaskRequest;

import java.util.UUID;

public interface PostTaskUseCase {
    UUID execute(CreateTaskRequest request);
}
