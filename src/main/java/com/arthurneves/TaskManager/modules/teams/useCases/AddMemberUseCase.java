package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.NotTeamOwner;
import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.exceptions.UserAlreadyOnTeam;
import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.AddMemberRequestDTO;
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
    
    public void execute(String token, AddMemberRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        Optional<TeamEntity> team = this.teamRepository.findById(UUID.fromString(data.getTeamId()));
        if (team.isEmpty()) {
            throw new TeamNotFound();
        }

        Optional<UserEntity> user = this.userRepository.findByEmail(data.getEmail());
        if (user.isEmpty()) {
            throw new UserNotFound();
        }

        // Usuário é o dono
        if (!team.get().getOwnerId().equals(id)) {
            throw new NotTeamOwner();
        }

        TeamEntity teamEntity = team.get();
        UserEntity userEntity = user.get();

        if (teamEntity.getMembers().contains(userEntity)) {
            throw new UserAlreadyOnTeam();
        };

        teamEntity.getMembers().add(user.get());
        this.teamRepository.save(teamEntity);
    }
}
