package com.arthurneves.TaskManager.modules.teams.repositories;

import com.arthurneves.TaskManager.modules.teams.TeamColumnEntity;
import com.arthurneves.TaskManager.modules.teams.entities.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColumnRepository extends JpaRepository<ColumnEntity, UUID> {
}
