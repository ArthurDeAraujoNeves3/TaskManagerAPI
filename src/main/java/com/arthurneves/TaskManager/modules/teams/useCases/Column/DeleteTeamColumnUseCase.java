package com.arthurneves.TaskManager.modules.teams.useCases.Column;

import com.arthurneves.TaskManager.exceptions.ColumnNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.DeleteColumnRequest;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.ColumnRepository;
import com.arthurneves.TaskManager.modules.teams.useCases.AddMemberUseCase;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.providers.JWTProvider;
import com.arthurneves.TaskManager.utils.GetUserIdFromJWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteTeamColumnUseCase {
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private GetUserIdFromJWToken getUserIdFromJWToken;
    @Autowired
    private AddMemberUseCase addMemberUseCase;
    @Autowired
    private CreateTeamColumnUseCase createTeamColumnUseCase;

    public String execute(String token, UUID columnId, DeleteColumnRequest data) {
        UUID id = this.getUserIdFromJWToken.get(token);

        Optional<ColumnEntity> column = this.columnRepository.findById(columnId);
        if (column.isEmpty()) {
            throw new ColumnNotFound();
        }

        TeamEntity teamEntity = this.addMemberUseCase.teamExists(data.getTeamId());
        UserEntity userEntity = this.createTeamColumnUseCase.userExists(id);
        this.createTeamColumnUseCase.userIsOnTheTeam(teamEntity, userEntity);
        this.columnRepository.findById(columnId);

        this.columnRepository.deleteById(columnId);
        return column.get().getName();
    }
}
