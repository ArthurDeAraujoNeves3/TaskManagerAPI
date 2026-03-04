package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.dto.ColumnCreateUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.useCases.Column.CreateTeamColumnUseCase;
import com.arthurneves.TaskManager.modules.teams.useCases.Column.UpdateTeamColumnUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    @Autowired
    private CreateTeamColumnUseCase createTeamColumnUseCase;
    @Autowired
    private UpdateTeamColumnUseCase updateTeamColumnUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> createColumnInTeam(@RequestHeader("Authorization") String token, @Valid @RequestBody ColumnCreateUpdateRequestDTO data) {
        try {
            this.createTeamColumnUseCase.execute(token, data);
            return ResponseEntity.ok().body(String.format("Coluna %s criada com sucesso", data.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateColumnInTeam(@PathVariable String id, @RequestHeader("Authorization") String token, @Valid @RequestBody ColumnCreateUpdateRequestDTO data) {
        try {
            this.updateTeamColumnUseCase.execute(UUID.fromString(id), token, data);
            return ResponseEntity.ok().body(String.format("Coluna %s formatada com sucesso", data.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
