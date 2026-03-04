package com.arthurneves.TaskManager.modules.teams.entities;

import com.arthurneves.TaskManager.modules.teams.TeamColumnEntity;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.*;

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

    @Length(min = 1, max = 72)
    private String description;

    private UUID ownerId;

    @ManyToOne()
    @JoinColumn(name = "ownerId", insertable = false, updatable = false)
    private UserEntity owner;

    @OneToMany(mappedBy = "team")
    private List<TeamColumnEntity> columns;

    @ManyToMany()
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "teamId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonManagedReference
    private List<UserEntity> members;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
