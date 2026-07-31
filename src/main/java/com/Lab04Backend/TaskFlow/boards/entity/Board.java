package com.Lab04Backend.TaskFlow.boards.entity;

import com.Lab04Backend.TaskFlow.boards.enums.BoardStatus;
import com.Lab04Backend.TaskFlow.task.entity.Task;
import com.Lab04Backend.TaskFlow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "boards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardStatus status;

    @ManyToMany
    @JoinTable(
            name = "board_users"
    )
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "board_admins"
    )
    @Builder.Default
    private List<User> admins = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    private Instant deletedAt;
}
