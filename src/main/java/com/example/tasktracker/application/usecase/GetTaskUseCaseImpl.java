package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.TaskResponse;
import com.example.tasktracker.application.mapper.TaskResponseMapper;
import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.task.exception.TaskNotFoundException;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetTaskUseCaseImpl implements GetTaskUseCase {
    private final TaskRepositoryPort repository;
    private final TaskResponseMapper mapper;

    public GetTaskUseCaseImpl(TaskRepositoryPort repository, TaskResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse execute(UUID id) {
        Task aggregate = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapper.toResponse(aggregate);
    }
}
