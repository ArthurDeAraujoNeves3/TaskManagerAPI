package com.arthurneves.TaskManager.modules.teams.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteColumnRequest {
    private UUID teamId;
}
