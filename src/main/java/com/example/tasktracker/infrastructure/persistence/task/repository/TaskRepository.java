package com.example.tasktracker.infrastructure.persistence.task.repository;

import com.example.tasktracker.infrastructure.persistence.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
