package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserFoundException;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String formatUsername(String name) {
        String[] nameParts = name.trim().toLowerCase().split("\\s");

        int length = nameParts.length;

        if (length == 1) {
            return nameParts[0];
        }

        return nameParts[0] + "." + nameParts[length - 1];
    }

    public UserEntity execute(UserEntity data) {
        // Criando username
        data.setUsername(this.formatUsername(data.getName()));

        // Email ou username ja cadastrados
        this.repository.findByUsernameOrEmail(data.getUsername(), data.getEmail()).ifPresent(user -> {
            throw new UserFoundException();
        });

        // Criptografando senha
        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        data.setPassword(encryptedPassword);

        return this.repository.save(data);
    }
}
