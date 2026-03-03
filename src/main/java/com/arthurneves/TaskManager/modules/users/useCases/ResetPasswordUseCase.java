package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserNotFound;
import com.arthurneves.TaskManager.exceptions.WrongPasswordException;
import com.arthurneves.TaskManager.modules.users.dto.UserResetPasswordRequestDTO;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordUseCase {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(String token, UserResetPasswordRequestDTO data) {
        UUID id = UUID.fromString(this.jwtProvider.validateToken(token));
        Optional<UserEntity> user = this.repository.findById(id);

        if ( user.isEmpty() ) {
            throw new UserNotFound();
        }

        UserEntity userEntity = user.get();
        boolean passwordMatches = passwordEncoder.matches(data.getPassword(), userEntity.getPassword());
        if ( !passwordMatches ) {
            throw new WrongPasswordException();
        }

        UserEntity updatedUser = UserEntity.builder()
                .id(id)
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(passwordEncoder.encode(data.getNewPassword()))
                .build();

        this.repository.save(updatedUser);
    }
}
