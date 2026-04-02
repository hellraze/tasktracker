package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.TaskResponse;

import java.util.UUID;

public interface GetTaskUseCase {
    TaskResponse execute(UUID id);
}
