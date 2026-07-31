package com.Lab04Backend.TaskFlow.auth.dtos;

import java.util.UUID;

public record LoginResponseDTO(

        String token,
        UUID id,
        String name,
        String email,
        String avatar

) {
}