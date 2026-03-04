package com.arthurneves.TaskManager.modules.tasks.controllers;

import com.arthurneves.TaskManager.modules.tasks.dto.CreateTaskRequestDTO;
import com.arthurneves.TaskManager.modules.tasks.useCases.CreateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private CreateTaskUseCase createTaskUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createTaskInColumn(@RequestHeader("Authorization") String token, @Valid @RequestBody CreateTaskRequestDTO data) {
        try {
            this.createTaskUseCase.execute(token, data);
            return ResponseEntity.ok().body("");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
