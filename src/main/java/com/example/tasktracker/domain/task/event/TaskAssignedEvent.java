package com.example.tasktracker.domain.task.event;

import java.time.Instant;
import java.util.UUID;

public record TaskAssignedEvent(UUID taskId, UUID assigneeId, Instant timestamp) {}
