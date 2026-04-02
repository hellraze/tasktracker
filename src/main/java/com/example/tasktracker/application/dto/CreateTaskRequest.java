package com.example.tasktracker.application.dto;

import com.example.tasktracker.domain.task.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTaskRequest(
        @Schema(description = "Название задачи", example = "Fix login bug")
        @NotBlank(message = "Name is required")
        String name,

        @Schema(description = "Описание", example = "User cannot login...")
        String description,

        @Schema(description = "Начальный статус", example = "CREATED")
        @NotNull(message = "Status is required")
        Status status,

        @Schema(description = "ID исполнителя (optional)", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID assigneeId
) {}
