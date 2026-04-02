package com.example.tasktracker.domain.user.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(id.toString());
    }
}
