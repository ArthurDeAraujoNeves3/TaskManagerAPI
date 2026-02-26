package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.modules.teams.dto.TeamDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetTeamDetailsUseCase {
    @Autowired
    private TeamRepository repository;

    public List<TeamDTO> execute(String teamId) {
        // So pode pegar os detalhes se estiver associado aquela tarefa
        return this.repository.findTeamById(UUID.fromString(teamId));
    }
}
