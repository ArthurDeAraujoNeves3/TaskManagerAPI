package com.arthurneves.TaskManager.modules.teams;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "TeamColumnsa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;
    private UUID teamId;

    private Byte order;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "teamId", insertable = false, updatable = false)
    private TeamEntity team;
}
