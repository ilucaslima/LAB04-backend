package com.Lab04Backend.TaskFlow.user.dtos;

import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UpdateUserDTO(

        String name,

        String avatar,

        LocalDate birthday
) {}
