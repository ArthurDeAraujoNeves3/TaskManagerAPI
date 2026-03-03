package com.arthurneves.TaskManager.modules.teams.controllers;

import com.arthurneves.TaskManager.modules.teams.dto.TeamMemberRequestDTO;
import com.arthurneves.TaskManager.modules.teams.useCases.AddMemberUseCase;
import com.arthurneves.TaskManager.modules.teams.useCases.DeleteMemberUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teams")
public class TeamMemberController {
    @Autowired
    private AddMemberUseCase addMemberUseCase;
    @Autowired
    private DeleteMemberUseCase deleteMemberUseCase;

    @PostMapping("/addMember")
    public ResponseEntity<Object> addMoreMembersInTeam(@RequestHeader("Authorization") String token, @Valid @RequestBody TeamMemberRequestDTO data) {
        try {
            this.addMemberUseCase.execute(token, data);
            return ResponseEntity.ok().body("Membro adicionado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/removeMember")
    public ResponseEntity<Object> removeMemberInTeam(@RequestHeader("Authorization") String token, @Valid @RequestBody TeamMemberRequestDTO data) {
        try {
            String member = this.deleteMemberUseCase.execute(token, data);
            return ResponseEntity.badRequest().body("Membro " + member + " foi removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
