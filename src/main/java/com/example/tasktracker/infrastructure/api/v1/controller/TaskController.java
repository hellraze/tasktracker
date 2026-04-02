package com.example.tasktracker.infrastructure.api.v1.controller;

import com.example.tasktracker.application.dto.AssignTaskRequest;
import com.example.tasktracker.application.dto.CreateTaskRequest;
import com.example.tasktracker.application.dto.TaskResponse;
import com.example.tasktracker.application.dto.UpdateTaskStatusRequest;
import com.example.tasktracker.application.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final GetTasksUseCase getTasksUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final PostTaskUseCase createTaskUseCase;
    private final UpdateTaskStatusUseCase updateTaskStatusUseCase;
    private final AssignTaskUseCase assignTaskUseCase;

    public TaskController(GetTasksUseCase getTasksUseCase, GetTaskUseCase getTaskUseCase,
                          PostTaskUseCase createTaskUseCase, UpdateTaskStatusUseCase updateTaskStatusUseCase,
                          AssignTaskUseCase assignTaskUseCase) {
        this.getTasksUseCase = getTasksUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.createTaskUseCase = createTaskUseCase;
        this.updateTaskStatusUseCase = updateTaskStatusUseCase;
        this.assignTaskUseCase = assignTaskUseCase;
    }

    @GetMapping("/")
    @Operation(summary = "Список задач с пагинацией")
    public Page<TaskResponse> getTasks(@ParameterObject Pageable pageable) {
        return getTasksUseCase.execute(pageable);
    }

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

    @PatchMapping("/{id}/status")
    @Operation(summary = "Сменить статус задачи")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Task not found")
    public ResponseEntity<Void> updateTaskStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskStatusRequest request) {
        updateTaskStatusUseCase.execute(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/assignee")
    @Operation(summary = "Назначить исполнителя задаче")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<Void> assignExecutor(
            @PathVariable UUID id,
            @Valid @RequestBody AssignTaskRequest request) {
        assignTaskUseCase.execute(id, request);
        return ResponseEntity.noContent().build();
    }

}

