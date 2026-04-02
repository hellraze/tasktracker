package com.example.tasktracker.application.mapper;

import com.example.tasktracker.application.dto.TaskResponse;
import com.example.tasktracker.domain.task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskResponseMapper {
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getAssigneeId()
        );
    }
}