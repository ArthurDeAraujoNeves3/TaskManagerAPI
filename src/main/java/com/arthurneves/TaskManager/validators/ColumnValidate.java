package com.arthurneves.TaskManager.validators;

import com.arthurneves.TaskManager.exceptions.ColumnNotFound;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class ColumnValidate {
    @Autowired
    private ColumnRepository columnRepository;

    public ColumnEntity columnExists(UUID columnId) {
        Optional<ColumnEntity> columnEntity = this.columnRepository.findById(columnId);
        if ( columnEntity.isEmpty() ) {
            throw new ColumnNotFound();
        }

        return columnEntity.get();
    }
}
