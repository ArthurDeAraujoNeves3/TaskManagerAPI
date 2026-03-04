package com.arthurneves.TaskManager.modules.teams.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ColumnCreateUpdateRequestDTO {
    private String name;
    private UUID teamId;
    private Byte order;
}
