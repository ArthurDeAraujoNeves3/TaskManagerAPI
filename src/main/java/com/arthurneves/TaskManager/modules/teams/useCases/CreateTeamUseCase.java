package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.SameTeamName;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateTeamUseCase {
    @Autowired
    private TeamRepository repository;
    @Autowired
    private JWTProvider jwtProvider;

    public TeamEntity execute(TeamEntity data, String token) {
        final UUID userId = UUID.fromString(jwtProvider.validateToken(token));

        Optional<TeamEntity> teamWithTheSameName = this.repository.findByOwnerIdAndName(userId, data.getName());

        if (teamWithTheSameName.isPresent()) {
            throw new SameTeamName();
        }

        data.setOwnerId(userId);
        System.out.println(data);
        this.repository.save(data);

        return data;
    }
}
