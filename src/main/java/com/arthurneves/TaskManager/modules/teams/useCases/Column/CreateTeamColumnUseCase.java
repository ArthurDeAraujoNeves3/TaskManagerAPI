package com.arthurneves.TaskManager.modules.teams.useCases.Column;

import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.ColumnCreateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.ColumnRepository;
import com.arthurneves.TaskManager.modules.teams.useCases.AddMemberUseCase;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.utils.GetUserIdFromJWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateTeamColumnUseCase {
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddMemberUseCase addMemberUseCase;
    @Autowired
    private GetUserIdFromJWToken getUserIdFromJWToken;

    public void execute(String token, ColumnCreateRequestDTO data) {
        UUID id = this.getUserIdFromJWToken.get(token);

        TeamEntity teamEntity = addMemberUseCase.teamExists(data.getTeamId());
        Optional<UserEntity> user = this.userRepository.findById(id);

        if ( user.isEmpty() ) {
            throw new UserNotFound();
        }

        byte length = (byte) this.columnRepository.findAll().toArray().length;
        ColumnEntity column = ColumnEntity.builder()
                .name(data.getName())
                .teamId(teamEntity.getId())
                .columnOrder(data.getOrder() != null ? data.getOrder() : length)
                .build();

        this.columnRepository.save(column);
    }
}
