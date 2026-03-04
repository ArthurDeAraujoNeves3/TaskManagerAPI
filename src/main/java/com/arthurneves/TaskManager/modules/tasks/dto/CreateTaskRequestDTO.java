package com.arthurneves.TaskManager.modules.tasks.dto;

import lombok.Data;

@Data
public class CreateTaskRequestDTO {
    private String name;
    private String description;
}
