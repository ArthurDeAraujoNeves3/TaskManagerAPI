package com.arthurneves.TaskManager.modules.users.dto;

import com.arthurneves.TaskManager.modules.teams.dto.TeamDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserGetInfoRequestDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private List<TeamDTO> teams;
}
