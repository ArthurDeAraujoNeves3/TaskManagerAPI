package com.arthurneves.TaskManager.modules.users.useCases;

import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    @Autowired
    private UserRepository repository;

    public UserEntity createUser(UserEntity body) {
        // Verificar se usuario ja existe (usernmae / email)

        // Criptografar senha

        String username = body.getName().toLowerCase().replaceAll("\\s", ".");
        body.setUsername(username);

        return this.repository.save(body);
    }
}
