package com.arthurneves.TaskManager.modules.teams.useCases;

import com.arthurneves.TaskManager.modules.teams.dto.TeamMemberRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteMemberUseCase {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AddMemberUseCase addMemberUseCase;

    public String execute(String token, TeamMemberRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        TeamEntity teamEntity = addMemberUseCase.teamExists(UUID.fromString(data.getTeamId()));
        UserEntity userEntity = addMemberUseCase.userExists(data.getEmail());
        addMemberUseCase.userIsTheOwner(teamEntity, id);

        if ( !teamEntity.getMembers().contains(userEntity) ) {
            throw new RuntimeException("Membro não pertence a essa equipe");
        }

        teamEntity.getMembers().remove(userEntity);
        this.teamRepository.save(teamEntity);

        return userEntity.getName();
    }
}
