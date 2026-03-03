package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.exceptions.NotTeamOwner;
import com.arthurneves.TaskManager.exceptions.TeamNotFound;
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
public class DeleteMemberUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private JWTProvider jwtProvider;

    public String execute(String token, TeamMemberRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        Optional<TeamEntity> team = this.teamRepository.findById(UUID.fromString(data.getTeamId()));
        if ( team.isEmpty() ) {
            throw new TeamNotFound();
        }

        Optional<UserEntity> user = this.userRepository.findByEmail(data.getEmail());
        if ( user.isEmpty() ) {
            throw new UserNotFound();
        }

        UserEntity userEntity = user.get();
        TeamEntity teamEntity = team.get();
        if ( !teamEntity.getOwnerId().equals(id) ) {
            throw new NotTeamOwner();
        }

        if ( !teamEntity.getMembers().contains(userEntity) ) {
            throw new RuntimeException("Membro não pertence a essa equipe");
        }

        teamEntity.getMembers().remove(userEntity);
        this.teamRepository.save(teamEntity);

        return userEntity.getName();
    }
}
