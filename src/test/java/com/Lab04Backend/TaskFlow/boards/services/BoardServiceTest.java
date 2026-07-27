//package com.Lab04Backend.TaskFlow.boards.services;
//
//import com.Lab04Backend.TaskFlow.boards.config.ResourceNotFoundException;
//import com.Lab04Backend.TaskFlow.boards.dtos.BoardResponse;
//import com.Lab04Backend.TaskFlow.boards.dtos.CreateBoardRequest;
//import com.Lab04Backend.TaskFlow.boards.models.Board;
//import com.Lab04Backend.TaskFlow.boards.models.BoardStatus;
//import com.Lab04Backend.TaskFlow.boards.repositories.BoardRepository;
//import com.Lab04Backend.TaskFlow.boards.services.BoardService;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class BoardServiceTest {
//
//    @Mock
//    private BoardRepository boardRepository;
//
//    @InjectMocks
//    private BoardService boardService;
//
//    @Test
//    void createBoard_Success() {
//        // Arrange
//        UUID userId = UUID.randomUUID();
//        UUID adminId = UUID.randomUUID();
//
//        CreateBoardRequest request = new CreateBoardRequest(
//            BoardStatus.ACTIVE,
//            List.of(userId),
//            List.of(adminId)
//        );
//
//        Board savedBoard = Board.builder()
//            .id(UUID.randomUUID())
//            .status(BoardStatus.ACTIVE)
//            .userIds(List.of(userId))
//            .adminIds(List.of(adminId))
//            .createdAt(Instant.now())
//            .updatedAt(Instant.now())
//            .build();
//
//        when(boardRepository.save(any(Board.class))).thenReturn(savedBoard);
//
//        // Act
//        BoardResponse response = boardService.createBoard(request);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(BoardStatus.ACTIVE, response.status());
//        assertTrue(response.userIds().contains(userId));
//        assertTrue(response.adminIds().contains(adminId));
//        verify(boardRepository, times(1)).save(any(Board.class));
//    }
//
//    @Test
//    void getBoardById_Success() {
//        // Arrange
//        UUID boardId = UUID.randomUUID();
//        Board board = Board.builder()
//            .id(boardId)
//            .status(BoardStatus.ACTIVE)
//            .createdAt(Instant.now())
//            .updatedAt(Instant.now())
//            .build();
//
//        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
//
//        // Act
//        BoardResponse response = boardService.getBoardById(boardId);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(boardId, response.id());
//    }
//
//    @Test
//    void getBoardById_SoftDeleted_ThrowsException() {
//        // Arrange
//        UUID boardId = UUID.randomUUID();
//        Board board = Board.builder()
//            .id(boardId)
//            .status(BoardStatus.ACTIVE)
//            .deletedAt(Instant.now())
//            .build();
//
//        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
//
//        // Act & Assert
//        assertThrows(ResourceNotFoundException.class, () -> boardService.getBoardById(boardId));
//    }
//
//    @Test
//    void deleteBoard_Success() {
//        // Arrange
//        UUID boardId = UUID.randomUUID();
//        Board board = Board.builder()
//            .id(boardId)
//            .status(BoardStatus.ACTIVE)
//            .build();
//
//        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
//
//        // Act
//        boardService.deleteBoard(boardId);
//
//        // Assert
//        assertNotNull(board.getDeletedAt());
//        verify(boardRepository, times(1)).save(board);
//    }
//}
