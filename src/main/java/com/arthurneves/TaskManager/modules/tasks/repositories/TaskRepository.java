package com.arthurneves.TaskManager.modules.tasks.repositories;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TeamEntity, UUID> {
}
