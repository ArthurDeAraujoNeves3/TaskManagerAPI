package com.arthurneves.TaskManager.modules.teams.repositories;

import com.arthurneves.TaskManager.modules.teams.entities.TeamMembersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamMembersRepository extends JpaRepository<TeamMembersEntity, UUID> {

}
