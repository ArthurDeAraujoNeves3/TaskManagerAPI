package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.dto.ColumnCreateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.useCases.Column.CreateTeamColumnUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    @Autowired
    private CreateTeamColumnUseCase createTeamColumnUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createColumnInTeam(@RequestHeader("Authorization") String token, @Valid @RequestBody ColumnCreateRequestDTO data) {
        try {
            this.createTeamColumnUseCase.execute(token, data);
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
