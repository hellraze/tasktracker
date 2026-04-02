package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.CreateTaskRequest;
import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import com.example.tasktracker.domain.user.exception.UserNotFoundException;
import com.example.tasktracker.domain.user.repository.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostTaskUseCaseImpl implements PostTaskUseCase {
    private final TaskRepositoryPort taskRepository;
    private final UserRepositoryPort userRepository;

    public PostTaskUseCaseImpl(TaskRepositoryPort taskRepository, UserRepositoryPort userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;

    }
    @Override
    public UUID execute(CreateTaskRequest request) {
        if (request.assigneeId() != null) {
            userRepository.findByID(request.assigneeId())
                    .orElseThrow(() -> new UserNotFoundException(request.assigneeId()));
        }

        Task newTask = new Task(
                UUID.randomUUID(),
                request.name(),
                request.description(),
                request.status(),
                request.assigneeId()
        );

        Task saved = taskRepository.save(newTask);

        sendTaskCreatedEvent(saved);

        return saved.getId();
    }

    public void sendTaskCreatedEvent(Task saved) {}
}
