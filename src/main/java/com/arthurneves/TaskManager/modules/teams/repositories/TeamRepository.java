package com.arthurneves.TaskManager.modules.teams.repositories;

import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {
    Optional<TeamEntity> findByName(String name);
}
