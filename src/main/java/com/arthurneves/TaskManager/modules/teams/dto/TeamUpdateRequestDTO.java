package com.arthurneves.TaskManager.modules.teams.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamUpdateRequestDTO {
    private String name;
    private String description;
}
