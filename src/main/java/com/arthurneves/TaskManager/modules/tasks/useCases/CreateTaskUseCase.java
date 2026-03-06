package com.arthurneves.TaskManager.modules.tasks.useCases;

import com.arthurneves.TaskManager.modules.tasks.dto.CreateTaskRequestDTO;
import com.arthurneves.TaskManager.modules.tasks.entities.TaskEntity;
import com.arthurneves.TaskManager.modules.tasks.repositories.TaskRepository;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.utils.GetUserIdFromJWToken;
import com.arthurneves.TaskManager.validators.ColumnValidate;
import com.arthurneves.TaskManager.validators.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTaskUseCase {
    @Autowired
    private GetUserIdFromJWToken getUserIdFromJWToken;
    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity execute(String token, CreateTaskRequestDTO data) {
        UUID id = this.getUserIdFromJWToken.get(token);

        Validate validate = new Validate();
        ColumnValidate columnValidate = new ColumnValidate();

        UserEntity user =  validate.userExists(id);
        ColumnEntity column = columnValidate.columnExists(data.getColumnId());
        validate.userBelongsToTeamOrIsOwner(column.getTeam(), user);

        TaskEntity task = TaskEntity.builder()
                .name(data.getName())
                .description(data.getDescription())
                .columnId(data.getColumnId())
                .build();

        return this.taskRepository.save(task);
    }
}
