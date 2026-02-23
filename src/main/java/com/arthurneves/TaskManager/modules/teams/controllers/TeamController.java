package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.useCases.CreateTeamUseCase;
import com.arthurneves.TaskManager.modules.teams.useCases.DeleteTeamUseCase;
import com.arthurneves.TaskManager.modules.teams.useCases.GetAllUserTeamsUseCase;
import com.arthurneves.TaskManager.modules.teams.useCases.GetTeamDetailsUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/teams")
@RestController
public class TeamController {
    @Autowired
    private CreateTeamUseCase createTeamUseCase;

    @Autowired
    private GetAllUserTeamsUseCase getAllUserTeams;

    @Autowired
    private GetTeamDetailsUseCase getTeamDetails;

    @Autowired
    private DeleteTeamUseCase deleteTeamUseCase;

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Object> deleteTeam(@RequestHeader("Authorization") String token, @PathVariable String id) {
        try {
            this.deleteTeamUseCase.execute(token, id);
            return ResponseEntity.ok().body("Time deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @GetMapping("/details/{id}")
    private ResponseEntity<Object> getTeamDetails(@PathVariable String id) {
        try {
            Optional<TeamEntity> team = this.getTeamDetails.execute(id);
            return ResponseEntity.ok().body(team);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> all(@RequestHeader("Authorization") String token) {
        try {
            List<TeamEntity> teams = this.getAllUserTeams.execute(token);
            return ResponseEntity.ok().body(teams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
