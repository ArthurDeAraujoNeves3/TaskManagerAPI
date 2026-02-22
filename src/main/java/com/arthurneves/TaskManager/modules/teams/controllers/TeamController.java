package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.dto.TeamCreateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.useCases.CreateTeamUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teams")
@RestController
public class TeamController {
    @Autowired
    private CreateTeamUseCase createTeamUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(TeamCreateRequestDTO data) {
        var dataa = createTeamUseCase.execute(data);
        return ResponseEntity.ok().body(dataa);
    }
}
