package com.example.tasktracker.infrastructure.persistence.user.mapper;

import com.example.tasktracker.domain.user.entity.User;
import com.example.tasktracker.infrastructure.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public UserEntity toEntity(User task) {
        return new UserEntity(
                task.getId(),
                task.getName(),
                task.getEmail()
        );
    }
}
