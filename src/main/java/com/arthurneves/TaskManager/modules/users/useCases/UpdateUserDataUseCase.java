package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.modules.users.dto.UserUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import com.arthurneves.TaskManager.utils.CreateUserUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUserDataUseCase {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTProvider jwtProvider;

    public UserUpdateRequestDTO execute(String token, UserUpdateRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));

        Optional<UserEntity> user = this.repository.findById(id);

        if ( user.isEmpty() ) {
            throw new UserNotFound();
        }

        String newUsername = CreateUserUsername.format(data.name());

        UserEntity userEntity = user.get();
        UserEntity userUpdated = UserEntity.builder()
                .id(id)
                .username(newUsername)
                .name(data.name())
                .email(data.email())
                .password(userEntity.getPassword())
                .build();

        this.repository.save(userUpdated);
        return data;
    }
}
