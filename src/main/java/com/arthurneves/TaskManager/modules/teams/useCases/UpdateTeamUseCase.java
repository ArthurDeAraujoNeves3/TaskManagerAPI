package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.SameTeamName;
import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.TeamUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateTeamUseCase {
    @Autowired
    private TeamRepository repository;

    @Autowired
    private JWTProvider jwtProvider;

    public TeamUpdateRequestDTO execute(String token, TeamUpdateRequestDTO data, UUID teamId) {
        UUID ownerId = UUID.fromString(this.jwtProvider.validateToken(token));

        Optional<TeamEntity> team = this.repository.findById(teamId);

        if (team.isEmpty()) {
            throw new TeamNotFound();
        }

        Optional<TeamEntity> nameAlreadyExists = this.repository.findByOwnerIdAndName(ownerId, data.getName());

        // Bloqueando o usuário de inserir times com o mesmo nome
        if ( nameAlreadyExists.isPresent() && teamId != nameAlreadyExists.get().getId() ) {
            throw new SameTeamName();
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
