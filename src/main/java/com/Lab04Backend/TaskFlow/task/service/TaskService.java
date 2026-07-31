package com.Lab04Backend.TaskFlow.task.service;

import com.Lab04Backend.TaskFlow.boards.entity.Board;
import com.Lab04Backend.TaskFlow.boards.repositories.BoardRepository;
import com.Lab04Backend.TaskFlow.task.dto.TaskRequest;
import com.Lab04Backend.TaskFlow.task.dto.TaskResponse;
import com.Lab04Backend.TaskFlow.task.entity.Task;
import com.Lab04Backend.TaskFlow.task.mapper.TaskMapper;
import com.Lab04Backend.TaskFlow.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;

    public TaskService(TaskRepository taskRepository, BoardRepository boardRepository) {
        this.taskRepository = taskRepository;
      this.boardRepository = boardRepository;
    }

    public TaskResponse create(TaskRequest request) {
        Board board = boardRepository.findById(request.getBoardId())
            .orElseThrow(() -> new RuntimeException("Board não encontrado."));

        Task task = TaskMapper.toEntity(request, board);

        task = taskRepository.save(task);

        task.setBoard(board);

        return TaskMapper.toResponse(task);
    }

    public List<TaskResponse> findAll() {

        return taskRepository.findByDeletedAtIsNull()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    public TaskResponse findById(UUID id) {

        Task task = taskRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada."));

        return TaskMapper.toResponse(task);
    }

    public TaskResponse update(UUID id, TaskRequest request) {

        Task task = taskRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada."));

        TaskMapper.updateEntity(task, request);

        task = taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }

    public void delete(UUID id) {

        Task task = taskRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada."));

        task.setDeletedAt(LocalDateTime.now());

        taskRepository.save(task);
    }
}