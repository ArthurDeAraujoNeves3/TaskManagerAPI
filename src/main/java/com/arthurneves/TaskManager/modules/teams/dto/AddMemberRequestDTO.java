package com.arthurneves.TaskManager.modules.teams.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AddMemberRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String teamId;
}
