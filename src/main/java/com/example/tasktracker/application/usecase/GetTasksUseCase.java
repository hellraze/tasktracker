package com.example.tasktracker.application.usecase;

import com.example.tasktracker.application.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetTasksUseCase {
    Page<TaskResponse> execute(Pageable pageable);
}
