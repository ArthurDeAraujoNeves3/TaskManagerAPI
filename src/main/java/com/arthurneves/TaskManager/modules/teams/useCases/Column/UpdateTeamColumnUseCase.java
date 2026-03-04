package com.arthurneves.TaskManager.modules.teams.useCases.Column;

import com.arthurneves.TaskManager.exceptions.ColumnNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.ColumnCreateUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.ColumnRepository;
import com.arthurneves.TaskManager.modules.teams.useCases.AddMemberUseCase;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.utils.GetUserIdFromJWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateTeamColumnUseCase {
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private GetUserIdFromJWToken getUserIdFromJWToken;
    @Autowired
    private AddMemberUseCase addMemberUseCase;
    @Autowired
    private CreateTeamColumnUseCase createTeamColumnUseCase;

    public void execute(UUID columnId, String token, ColumnCreateUpdateRequestDTO data) {
        UUID id = this.getUserIdFromJWToken.get(token);

        Optional<ColumnEntity> column = this.columnRepository.findById(columnId);
        if (column.isEmpty()) {
            throw new ColumnNotFound();
        }

        TeamEntity teamEntity = this.addMemberUseCase.teamExists(data.getTeamId());
        UserEntity userEntity = this.createTeamColumnUseCase.userExists(id);
        this.createTeamColumnUseCase.userIsOnTheTeam(teamEntity, userEntity);
        this.createTeamColumnUseCase.reorderColumns(data.getOrder());

        ColumnEntity columnEntity = column.get();
        ColumnEntity columnUpdated = ColumnEntity.builder()
                .id(columnId)
                .teamId(data.getTeamId())
                .name(data.getName() != null ? data.getName() : columnEntity.getName())
                .columnOrder(data.getOrder() != null ? data.getOrder() : columnEntity.getColumnOrder())
                .build();

        this.columnRepository.save(columnUpdated);
    }
}
