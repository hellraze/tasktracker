package com.example.tasktracker.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignTaskRequest (
        @Schema(description = "Новый статус", example = "IN_PROGRESS")
        @NotNull
        UUID assigneeId
){}
