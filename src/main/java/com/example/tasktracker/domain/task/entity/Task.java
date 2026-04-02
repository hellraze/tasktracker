package com.example.tasktracker.domain.task.entity;

import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private String description;
    private Status status;
    private UUID assigneeId;

    public Task(UUID id, String name, String description, Status status, UUID assigneeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public UUID getAssigneeId() { return assigneeId; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(Status status) { this.status = status; }
    public void setAssigneeId(UUID assigneeId) { this.assigneeId = assigneeId; }
}
