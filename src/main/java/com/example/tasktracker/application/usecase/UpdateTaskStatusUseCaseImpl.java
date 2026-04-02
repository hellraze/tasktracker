package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.UpdateTaskStatusRequest;
import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.task.exception.TaskNotFoundException;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UpdateTaskStatusUseCaseImpl implements UpdateTaskStatusUseCase {
    private final TaskRepositoryPort taskRepository;

    public UpdateTaskStatusUseCaseImpl(TaskRepositoryPort taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(UUID taskId, UpdateTaskStatusRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setStatus(request.status());
        taskRepository.save(task);
    }
}
