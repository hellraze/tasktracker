package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.AssignTaskRequest;

import java.util.UUID;

public interface AssignTaskUseCase {
    void execute(UUID taskId, AssignTaskRequest request);
}
