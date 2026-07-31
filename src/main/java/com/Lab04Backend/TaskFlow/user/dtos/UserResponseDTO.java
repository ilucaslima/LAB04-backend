package com.Lab04Backend.TaskFlow.user.dtos;

import com.Lab04Backend.TaskFlow.user.entity.User;

import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String name,
        String email,
        String avatar

) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAvatar()
        );
    }
}
