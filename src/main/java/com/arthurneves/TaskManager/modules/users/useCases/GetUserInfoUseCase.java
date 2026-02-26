package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.teams.dto.TeamDTO;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.arthurneves.TaskManager.modules.teams.repositories.TeamRepository;
import com.arthurneves.TaskManager.modules.users.dto.UserGetInfoRequestDTO;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GetUserInfoUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public UserGetInfoRequestDTO execute(String token) {
        UUID id = UUID.fromString(jwtProvider.validateToken(token));

        Optional<UserEntity> user = userRepository.findById(id);

        if ( user.isEmpty() ) {
            throw new UserNotFound();
        }

        List<TeamDTO> teams = teamRepository.findAllByOwnerId(id);
        UserEntity userEntity = user.get();

        return UserGetInfoRequestDTO.builder()
                .id(id)
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .teams(teams)
                .build();
    }
}
