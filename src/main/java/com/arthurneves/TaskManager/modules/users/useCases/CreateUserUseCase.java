package com.arthurneves.TaskManager.modules.users.useCases;

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

        if ( length == 1 ) {
            return nameParts[0];
        };

        return nameParts[0] + "." + nameParts[length - 1];
    }

    public UserEntity createUser(UserEntity body) {
        // Verificar se usuario ja existe (usernmae / email)

        // Criptografar senha

        body.setUsername(this.formatUsername(body.getName()));

        return this.repository.save(body);
    }
}
