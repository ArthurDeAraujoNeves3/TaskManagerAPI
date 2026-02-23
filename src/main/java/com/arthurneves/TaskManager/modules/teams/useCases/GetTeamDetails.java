package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetTeamDetails {
    @Autowired
    private TeamRepository repository;

    public Optional<TeamEntity> execute(String teamId) {
        return this.repository.findById(UUID.fromString(teamId));
    }
}
