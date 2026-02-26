package com.arthurneves.TaskManager.modules.teams.dto;

import com.arthurneves.TaskManager.modules.users.dto.UserRelationDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TeamDTO {
    UUID getId();
    String getName();
    String getDescription();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    UUID getOwnerId();
    UserRelationDTO getOwner();
}
