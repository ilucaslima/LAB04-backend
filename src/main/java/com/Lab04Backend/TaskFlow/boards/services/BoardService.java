package com.Lab04Backend.TaskFlow.boards.services;

import com.Lab04Backend.TaskFlow.boards.config.ResourceNotFoundException;
import com.Lab04Backend.TaskFlow.boards.dtos.BoardResponse;
import com.Lab04Backend.TaskFlow.boards.dtos.CreateBoardRequest;
import com.Lab04Backend.TaskFlow.boards.entity.Board;
import com.Lab04Backend.TaskFlow.boards.repositories.BoardRepository;

import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public BoardResponse createBoard(CreateBoardRequest request) {
        System.out.println("->" + request.getClass());
        System.out.println(request.usersIds());

        List<User> users = userRepository.findAllById(request.usersIds());
        List<User> admins = userRepository.findAllById(request.adminsIds());

        Board board = Board.builder()
            .status(request.status())
            .users(users)
            .admins(admins)
            .build();

        Board savedBoard = boardRepository.save(board);
        return BoardResponse.fromEntity(savedBoard);
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoardById(UUID id) {
        Board board = boardRepository.findById(id)
            .filter(b -> b.getDeletedAt() == null)
            .orElseThrow(() -> new ResourceNotFoundException("Quadro com ID " + id + " não encontrado."));
        return BoardResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse> listAllActiveBoards() {
        return boardRepository.findByDeletedAtIsNull().stream()
            .map(BoardResponse::fromEntity)
            .toList();
    }

    @Transactional
    public void deleteBoard(UUID id) {
        Board board = boardRepository.findById(id)
            .filter(b -> b.getDeletedAt() == null)
            .orElseThrow(() -> new ResourceNotFoundException("Quadro com ID " + id + " não encontrado."));

        board.setDeletedAt(Instant.now());
        boardRepository.save(board);
    }
}
