package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.exceptions.UserFoundException;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import com.arthurneves.TaskManager.utils.CreateUserUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCase {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(UserEntity data) {
        // Criando username
        data.setUsername(CreateUserUsername.format(data.getName()));

        // Email ou username ja cadastrados
        this.repository.findByUsernameOrEmail(data.getUsername(), data.getEmail()).ifPresent(user -> {
            throw new UserFoundException();
        });

        // Criptografando senha
        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        data.setPassword(encryptedPassword);

        this.repository.save(data);
    }
}
