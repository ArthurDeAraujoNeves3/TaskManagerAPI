package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.TeamUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateTeamUseCase {
    @Autowired
    private TeamRepository repository;

    public TeamUpdateRequestDTO execute(TeamUpdateRequestDTO data, UUID teamId) {
        Optional<TeamEntity> team = this.repository.findById(teamId);

        if (team.isEmpty()) {
            throw new TeamNotFound();
        }

        TeamEntity teamEntity = team.get();

        TeamEntity entity = TeamEntity.builder()
                .id(teamId)
                .name(data.getName() != null ? data.getName() : teamEntity.getName())
                .description(data.getDescription() != null ? data.getDescription() : teamEntity.getDescription())
                .ownerId(teamEntity.getOwnerId())
                .build();

        this.repository.save(entity);
        return data;
    }
}
