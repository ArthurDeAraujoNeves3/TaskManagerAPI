package com.arthurneves.TaskManager.modules.tasks.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTaskRequestDTO {
    private String name;
    private String description;
    private UUID columnId;
}
