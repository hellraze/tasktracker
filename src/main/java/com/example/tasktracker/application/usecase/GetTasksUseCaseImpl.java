package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.TaskResponse;
import com.example.tasktracker.application.mapper.TaskResponseMapper;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetTasksUseCaseImpl implements GetTasksUseCase {
    private final TaskRepositoryPort taskRepository;
    private final TaskResponseMapper mapper;

    public GetTasksUseCaseImpl(TaskRepositoryPort taskRepository, TaskResponseMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<TaskResponse> execute(Pageable pageable) {
        return taskRepository.getTasks(pageable).map(mapper::toResponse);
    }
}
