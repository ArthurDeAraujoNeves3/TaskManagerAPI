package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserFoundException;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository repository;

    private String formatUsername(String name) {
        String[] nameParts = name.trim().toLowerCase().split("\\s");

        int length = nameParts.length;

        if (length == 1) {
            return nameParts[0];
        }

        return nameParts[0] + "." + nameParts[length - 1];
    }

    public UserEntity execute(UserEntity body) {
        // Username
        body.setUsername(this.formatUsername(body.getName()));

        // Email ou username ja cadastrados
        this.repository.findByUsernameOrEmail(body.getUsername(), body.getEmail()).ifPresent(user -> {
            throw new UserFoundException();
        });

        // Criptografar senha

        return this.repository.save(body);
    }
}
