package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.SameTeamName;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateTeamUseCase {
    @Autowired
    private TeamRepository repository;
    @Autowired
    private JWTProvider jwtProvider;

    public TeamEntity execute(TeamEntity data, String token) {
        UUID id = UUID.fromString(jwtProvider.validateToken(token));

        List<TeamEntity> userTeams = this.repository.findAllByOwner(id);
        userTeams.forEach(team -> {
            if (Objects.equals(team.getName(), data.getName())) {
                throw new SameTeamName();
            };
        });

        data.setOwner(id);
        this.repository.save(data);

        return data;
    }
}
