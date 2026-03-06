package com.arthurneves.TaskManager.validators;

import com.arthurneves.TaskManager.exceptions.TeamNotFound;
import com.arthurneves.TaskManager.exceptions.UserIsNotOnTeam;
import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

// ESES VALIDATE QUE EU FIZ, NA REALIDADE ERA MELHOR FAZER MIDDLEWARES

public class Validate {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    public UserEntity userExists(UUID userId) {
        Optional<UserEntity> userEntity = this.userRepository.findById(userId);
        if ( userEntity.isEmpty() ) {
            throw new UserNotFound();
        }

        return userEntity.get();
    }

    public TeamEntity teamExists(UUID teamId) {
        Optional<TeamEntity> teamEntity = this.teamRepository.findById(teamId);
        if (teamEntity.isEmpty()) {
            throw new TeamNotFound();
        }

        return teamEntity.get();
    }

    public void userBelongsToTeamOrIsOwner(TeamEntity team, UserEntity user) {
        if ( !team.getMembers().contains(user) && !team.getOwnerId().equals(user.getId()) ) {
            throw new UserIsNotOnTeam();
        }
    }
}
