package com.arthurneves.TaskManager.modules.teams.entities;

import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "Teams")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @Length(min = 0, max = 72)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private UUID ownerId;

    @ManyToOne()
    @JoinColumn(name = "ownerId", insertable = false, updatable = false)
    private UserEntity owner;
}
