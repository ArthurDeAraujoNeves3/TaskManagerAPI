package com.arthurneves.TaskManager.modules.teams.useCases.Column;

import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.ColumnCreateUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.ColumnRepository;
import com.arthurneves.TaskManager.modules.teams.useCases.AddMemberUseCase;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.utils.GetUserIdFromJWToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CreateTeamColumnUseCase {
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddMemberUseCase addMemberUseCase;
    @Autowired
    private GetUserIdFromJWToken getUserIdFromJWToken;

    public void userExists(UUID id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFound();
        }
    }

    public void reorderColumns(Byte order) {
        if (order != null) {
            Optional<ColumnEntity> orderInTable = this.columnRepository.findByColumnOrder(order);
            if (orderInTable.isPresent()) {
                this.columnRepository.reorderColumns(order);
            }
        }
    }

    public void execute(String token, ColumnCreateUpdateRequestDTO data) {
        UUID id = this.getUserIdFromJWToken.get(token);

        TeamEntity teamEntity = addMemberUseCase.teamExists(data.getTeamId());
        this.userExists(id);
        this.reorderColumns(data.getOrder());

        byte length = (byte) this.columnRepository.findAll().toArray().length;
        ColumnEntity column = ColumnEntity.builder()
                .name(data.getName())
                .teamId(teamEntity.getId())
                .columnOrder(data.getOrder() != null ? data.getOrder() : length)
                .build();

        this.columnRepository.save(column);
    }
}
