package com.Lab04Backend.TaskFlow.boards.dtos;

import com.Lab04Backend.TaskFlow.boards.enums.BoardStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateBoardRequest(
    @NotNull(message = "Status é obrigatório")
    BoardStatus status,

    List<UUID> usersIds,

    List<UUID> adminsIds
) {}