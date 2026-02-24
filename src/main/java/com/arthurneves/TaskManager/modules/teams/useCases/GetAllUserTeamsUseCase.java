package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAllUserTeamsUseCase {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private TeamRepository repository;

    public List<TeamEntity> execute(String token) {
        final UUID userId = UUID.fromString(jwtProvider.validateToken(token));
        return this.repository.findAllByOwnerId(userId);
    }
}
