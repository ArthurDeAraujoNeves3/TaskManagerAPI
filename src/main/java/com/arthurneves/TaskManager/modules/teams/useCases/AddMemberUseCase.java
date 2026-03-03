package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.NotTeamOwner;
import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.exceptions.UserAlreadyOnTeam;
import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.TeamMemberRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddMemberUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private JWTProvider jwtProvider;

    protected TeamEntity teamExists(UUID teamId) {
        Optional<TeamEntity> team = this.teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new TeamNotFound();
        }

        return team.get();
    }

    protected UserEntity userExists(String email) {
        Optional<UserEntity> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFound();
        }

        return user.get();
    }

    protected void userIsTheOwner(TeamEntity team, UUID userId) {
        if (!team.getOwnerId().equals(userId)) {
            throw new NotTeamOwner();
        }
    }

    public void execute(String token, TeamMemberRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        // Validacoes
        TeamEntity teamEntity = this.teamExists(UUID.fromString(data.getTeamId()));
        UserEntity userEntity = this.userExists(data.getEmail());
        this.userIsTheOwner(teamEntity, id);

        // Usuario ja esta no time
        if (teamEntity.getMembers().contains(userEntity)) {
            throw new UserAlreadyOnTeam();
        }

        teamEntity.getMembers().add(userEntity);
        this.teamRepository.save(teamEntity);
    }
}
