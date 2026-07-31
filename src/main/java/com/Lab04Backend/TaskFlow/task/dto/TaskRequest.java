package com.Lab04Backend.TaskFlow.task.dto;

import com.Lab04Backend.TaskFlow.boards.entity.Board;
import com.Lab04Backend.TaskFlow.task.enums.BoardStatus;
import com.Lab04Backend.TaskFlow.task.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @NotNull
    private BoardStatus status;

    @NotNull
    private Priority priority;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Long member;

    private String time;

    private UUID boardId;
}