package com.arthurneves.TaskManager.modules.tasks.entities;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    @Max(300)
    private String comment;

    private UUID authorId;
    private UUID taskId;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "authorId", insertable = false, updatable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "taskId", insertable = false, updatable = false)
    private TaskEntity task;
}
