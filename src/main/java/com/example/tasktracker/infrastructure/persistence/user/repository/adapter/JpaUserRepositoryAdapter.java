package com.example.tasktracker.infrastructure.persistence.user.repository.adapter;

import com.example.tasktracker.domain.user.entity.User;
import com.example.tasktracker.domain.user.repository.UserRepositoryPort;
import com.example.tasktracker.infrastructure.persistence.user.entity.UserEntity;
import com.example.tasktracker.infrastructure.persistence.user.mapper.UserEntityMapper;
import com.example.tasktracker.infrastructure.persistence.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    private final UserRepository jpaRepo;
    private final UserEntityMapper mapper;

    public JpaUserRepositoryAdapter(UserRepository jpaRepo, UserEntityMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByID(UUID id) { return jpaRepo.findById(id).map(mapper::toDomain); }

    @Override
    public Optional<User> save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepo.save(entity);
        return Optional.ofNullable(mapper.toDomain(saved));
    }
}
