package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.NotTeamOwner;
import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteTeamUseCase {
    @Autowired
    private TeamRepository repository;

    @Autowired
    private JWTProvider jwtProvider;

    public void execute(String token, String teamId) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        Optional<TeamEntity> team = this.repository.findById(UUID.fromString(teamId));
        if (team.isEmpty()) {
            throw new TeamNotFound();
        }

        UUID teamOwner = team.get().getOwnerId();
        if (!teamOwner.equals(id)) {
            throw new NotTeamOwner();
        }

        this.repository.deleteById(UUID.fromString(teamId));
    }
}
