package com.arthurneves.TaskManager.modules.teams.entities;

import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "TeamMembers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamMembersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;
    private UUID teamId;

    @ManyToOne()
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne()
    @JoinColumn(name = "teamId", insertable = false, updatable = false)
    private TeamEntity teamEntity;

    @CreationTimestamp
    private LocalDateTime creationAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
