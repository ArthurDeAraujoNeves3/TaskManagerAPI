package com.arthurneves.TaskManager.modules.teams.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String teamId;
}
