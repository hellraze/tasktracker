package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.UpdateTaskStatusRequest;

import java.util.UUID;

public interface UpdateTaskStatusUseCase {
    void execute(UUID taskId, UpdateTaskStatusRequest request);
}
