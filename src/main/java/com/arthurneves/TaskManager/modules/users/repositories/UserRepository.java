package com.arthurneves.TaskManager.modules.users.repositories;

import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
