package com.example.tasktracker.domain.task.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(UUID id) {
        super(id.toString());
    }
}
