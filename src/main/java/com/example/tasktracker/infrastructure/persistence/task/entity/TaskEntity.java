package com.example.tasktracker.infrastructure.persistence.task.entity;

import com.example.tasktracker.domain.task.entity.Status;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
        name = "tasks",
        schema = "tasktracker_schema"
)
public class TaskEntity{
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "assignee_id", nullable = true)
    private UUID assigneeId;

    protected TaskEntity() {}

    public TaskEntity(UUID id, String name, String description, Status status, UUID assigneeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
    }

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return this.id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", description = " + description +
                ", status = " + status +
                ", assigneeId = " + assigneeId +
                '}';
    }

}
