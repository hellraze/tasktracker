package com.example.tasktracker.infrastructure.persistence.task.repository.adapter;


import com.example.tasktracker.domain.task.entity.Task;
import com.example.tasktracker.domain.task.repository.TaskRepositoryPort;
import com.example.tasktracker.domain.user.entity.User;
import com.example.tasktracker.infrastructure.persistence.task.entity.TaskEntity;
import com.example.tasktracker.infrastructure.persistence.task.mapper.TaskEntityMapper;
import com.example.tasktracker.infrastructure.persistence.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {
    private final TaskRepository jpaRepo;
    private final TaskEntityMapper mapper;

    public JpaTaskRepositoryAdapter(TaskRepository jpaRepo, TaskEntityMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return jpaRepo.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Task> findById(UUID id) { return jpaRepo.findById(id).map(mapper::toDomain); }

    @Override
    public Task save(Task task){
        TaskEntity entity = mapper.toEntity(task);
        TaskEntity saved = jpaRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public UUID assign(Task task, User user) {
        task.setAssigneeId(user.getId());
        TaskEntity taskEntity = mapper.toEntity(task);
        TaskEntity saved = jpaRepo.save(taskEntity);
        return saved.getAssigneeId();
    }

}
