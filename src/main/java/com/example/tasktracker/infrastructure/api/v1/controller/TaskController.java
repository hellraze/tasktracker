package com.example.tasktracker.infrastructure.api.v1.controller;

import com.example.tasktracker.application.dto.CreateTaskRequest;
import com.example.tasktracker.application.dto.TaskResponse;
import com.example.tasktracker.application.usecase.GetTaskUseCase;
import com.example.tasktracker.application.usecase.PostTaskUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final GetTaskUseCase getTaskUseCase;
    private final PostTaskUseCase createTaskUseCase;

    public TaskController(GetTaskUseCase getTaskUseCase, PostTaskUseCase createTaskUseCase) {
        this.getTaskUseCase = getTaskUseCase;
        this.createTaskUseCase = createTaskUseCase;
    }

    @GetMapping("/")
    public void getTasks() {}

    @GetMapping("/{id}")
    @Operation(summary = "Получить задачу по id",
            description = "Возвращает задачу согласно полученному uuid")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Internal error")
    public ResponseEntity<TaskResponse> getTaskById(
            @Parameter(description = "ID задачи", required = true)
            @PathVariable UUID id) {
        TaskResponse response = getTaskUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    @Operation(summary = "Создать задачу")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<UUID> createTask(@Valid @RequestBody CreateTaskRequest request) {
        UUID id = createTaskUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/v1/tasks/" + id)).body(id);
    }

}

