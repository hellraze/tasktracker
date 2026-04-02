package com.example.tasktracker.infrastructure.persistence.task.mapper;

import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.infrastructure.persistence.task.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskEntityMapper {
    public Task toDomain(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getAssigneeId()
        );
    }

    public TaskEntity toEntity(Task task) {
        return new TaskEntity(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getAssigneeId()
        );
    }
}