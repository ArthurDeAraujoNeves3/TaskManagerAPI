package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.modules.teams.dto.TeamCreateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTeamUseCase {
    @Autowired
    private TeamRepository repository;

    public TeamCreateRequestDTO execute(TeamCreateRequestDTO data) {
        return data;
    }
}
