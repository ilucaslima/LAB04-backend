package com.Lab04Backend.TaskFlow.user.controllers;

import com.Lab04Backend.TaskFlow.user.dtos.RegisterUserDTO;
import com.Lab04Backend.TaskFlow.user.dtos.UserResponseDTO;
import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.Lab04Backend.TaskFlow.user.dtos.UpdateUserDTO;

@Tag(name="Users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody RegisterUserDTO dto
    ) {

        User user = service.register(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponseDTO(user));
    }
    @PutMapping
    public ResponseEntity<UserResponseDTO> update(
            Authentication authentication,
            @RequestBody UpdateUserDTO dto
    ){

        User user = service.update(authentication, dto);


        return ResponseEntity.ok(
                new UserResponseDTO(user)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(
            Authentication authentication
    ){

        User user = service.getAuthenticatedUser(authentication);

        return ResponseEntity.ok(
                new UserResponseDTO(user)
        );
    }
    @DeleteMapping
    public ResponseEntity<Void> delete(
            Authentication authentication
    ){

        service.delete(authentication);

        return ResponseEntity.noContent().build();
    }
}