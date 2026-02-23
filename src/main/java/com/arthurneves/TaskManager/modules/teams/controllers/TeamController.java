package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.dto.TeamCreateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.useCases.CreateTeamUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/teams")
@RestController
public class TeamController {
    @Autowired
    private CreateTeamUseCase createTeamUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody TeamEntity data, @RequestHeader("Authorization") String token) {
        try {
            var result = createTeamUseCase.execute(data, token);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
