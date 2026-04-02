package com.example.tasktracker.application.dto;

import com.example.tasktracker.domain.task.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record TaskResponse (
    @Schema(description = "Уникальный ID",
            example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id,

    @Schema(description = "Название задачи", example = "Подготовить квартальный отчет", maxLength = 255)
    String name,

    @Schema(description = "Описание", example = "Подготовить квартальный отчет по продажам")
    String description,

    @Schema(description = "Статус задачи", example = "")
    Status status,

    @Schema(description = "Назначенгный на задачу сотрдник", example = "Иванов Иван Иванович")
    UUID assigneeId
    ) {}
