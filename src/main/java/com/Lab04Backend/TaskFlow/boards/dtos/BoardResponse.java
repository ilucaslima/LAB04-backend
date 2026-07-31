package com.Lab04Backend.TaskFlow.boards.dtos;

import com.Lab04Backend.TaskFlow.boards.enums.BoardStatus;
import com.Lab04Backend.TaskFlow.boards.entity.Board;
import com.Lab04Backend.TaskFlow.user.entity.User;


import java.time.Instant;
import java.util.List;
import java.util.UUID;


public record BoardResponse(
    UUID id,
    BoardStatus status,
    List<User> users,
    List<User> admins,
    // List<UUID> taskIds,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {
    public static BoardResponse fromEntity(Board board) {
        return new BoardResponse(
            board.getId(),
            board.getStatus(),
            board.getUsers(),
            board.getAdmins(),
            board.getCreatedAt(),
            board.getUpdatedAt(),
            board.getDeletedAt()
        );
    }
}
