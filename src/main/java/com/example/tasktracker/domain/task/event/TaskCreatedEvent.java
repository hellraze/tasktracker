package com.example.tasktracker.domain.task.event;

import java.time.Instant;
import java.util.UUID;

public record TaskCreatedEvent(UUID taskId, String name, String description, UUID assigneeId, Instant timestamp) {}
