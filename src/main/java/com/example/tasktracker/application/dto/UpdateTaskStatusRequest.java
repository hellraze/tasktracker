package com.example.tasktracker.application.dto;

import com.example.tasktracker.domain.task.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(
        @Schema(description = "Новый статус", example = "IN_PROGRESS")
        @NotNull
        Status status
) {}
