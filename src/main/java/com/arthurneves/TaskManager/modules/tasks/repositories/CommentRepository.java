package com.arthurneves.TaskManager.modules.tasks.repositories;

import com.arthurneves.TaskManager.modules.tasks.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
