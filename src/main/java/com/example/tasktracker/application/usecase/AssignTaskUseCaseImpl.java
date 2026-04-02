package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.AssignTaskRequest;
import com.example.tasktracker.domain.task.entity.Status;
import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.task.event.TaskAssignedEvent;
import com.example.tasktracker.domain.task.exception.TaskNotFoundException;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import com.example.tasktracker.domain.user.exception.UserNotFoundException;
import com.example.tasktracker.domain.user.repository.UserRepositoryPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AssignTaskUseCaseImpl implements AssignTaskUseCase{
    private final TaskRepositoryPort taskRepository;
    private final UserRepositoryPort userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AssignTaskUseCaseImpl(TaskRepositoryPort taskRepository, UserRepositoryPort userRepository,
                                 KafkaTemplate<String, Object> kafkaTemplate) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void execute(UUID taskId, AssignTaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        if (request.assigneeId() != null) {
            userRepository.findByID(request.assigneeId())
                    .orElseThrow(() -> new UserNotFoundException(request.assigneeId()));
        }

        task.setStatus(Status.ASSIGNED);
        task.setAssigneeId(request.assigneeId());
        taskRepository.save(task);
        sendTaskAssignedEvent(task);
    }

    private void sendTaskAssignedEvent(Task task) {
        TaskAssignedEvent event = new TaskAssignedEvent(task.getId(), task.getAssigneeId(), Instant.now());
        kafkaTemplate.send("task.assigned", task.getId().toString(), event);
    }
}
